package tracer

import (
	"encoding/binary"
	"io"
	"strconv"
	"strings"

	"github.com/gogo/protobuf/proto"
	opentracing "github.com/opentracing/opentracing-go"
	"github.com/wavefronthq/wavefront-opentracing-sdk-go/wire"
)

const (
	prefixBaggage = "wf-ot-"

	fieldNameTraceID = prefixBaggage + "traceid"
	fieldNameSpanID  = prefixBaggage + "spanid"
	fieldNameSampled = prefixBaggage + "sample"
)

type accessorPropagator struct {
	tracer *WavefrontTracer
}

type textMapPropagator struct {
	tracer *WavefrontTracer
}

type binaryPropagator struct {
	tracer *WavefrontTracer
}

// DelegatingCarrier is a flexible carrier interface which can be implemented
// by types which have a means of storing the trace metadata and already know
// how to serialize themselves (for example, protocol buffers).
type DelegatingCarrier interface {
	SetState(traceID string, spanID string, sampled bool)
	State() (traceID string, spanID string, sampled bool)
	SetBaggageItem(key, value string)
	GetBaggage(func(key, value string))
}

func (p *accessorPropagator) Inject(spanContext opentracing.SpanContext, carrier interface{}) error {
	dc, ok := carrier.(DelegatingCarrier)
	if !ok || dc == nil {
		return opentracing.ErrInvalidCarrier
	}
	sc, ok := spanContext.(SpanContext)
	if !ok {
		return opentracing.ErrInvalidSpanContext
	}
	dc.SetState(sc.TraceID, sc.SpanID, !sc.IsSampled() || *sc.Sampled)
	for k, v := range sc.Baggage {
		dc.SetBaggageItem(k, v)
	}
	return nil
}

func (p *accessorPropagator) Extract(carrier interface{}) (opentracing.SpanContext, error) {
	dc, ok := carrier.(DelegatingCarrier)
	if !ok || dc == nil {
		return nil, opentracing.ErrInvalidCarrier
	}

	traceID, spanID, sampled := dc.State()
	sc := SpanContext{
		TraceID: traceID,
		SpanID:  spanID,
		Sampled: &sampled,
		Baggage: nil,
	}
	dc.GetBaggage(func(k, v string) {
		if sc.Baggage == nil {
			sc.Baggage = map[string]string{}
		}
		sc.Baggage[k] = v
	})
	return sc, nil
}

func (p *textMapPropagator) Inject(spanContext opentracing.SpanContext, opaqueCarrier interface{}) error {
	sc, ok := spanContext.(SpanContext)
	if !ok {
		return opentracing.ErrInvalidSpanContext
	}
	carrier, ok := opaqueCarrier.(opentracing.TextMapWriter)
	if !ok {
		return opentracing.ErrInvalidCarrier
	}
	carrier.Set(fieldNameTraceID, sc.TraceID)
	carrier.Set(fieldNameSpanID, sc.SpanID)
	if sc.IsSampled() {
		carrier.Set(fieldNameSampled, strconv.FormatBool(*sc.SamplingDecision()))
	}

	for k, v := range sc.Baggage {
		carrier.Set(prefixBaggage+k, v)
	}
	return nil
}

func (p *textMapPropagator) Extract(opaqueCarrier interface{}) (opentracing.SpanContext, error) {
	carrier, ok := opaqueCarrier.(opentracing.TextMapReader)
	if !ok {
		return nil, opentracing.ErrInvalidCarrier
	}

	result := SpanContext{Baggage: make(map[string]string)}
	var err error

	err = carrier.ForeachKey(func(k, v string) error {
		lowercaseK := strings.ToLower(k)
		switch lowercaseK {
		case fieldNameTraceID:
			result.TraceID = v
		case fieldNameSpanID:
			result.SpanID = v
		case fieldNameSampled:
			decision, err := strconv.ParseBool(v)
			if err != nil {
				return opentracing.ErrSpanContextCorrupted
			}
			result.Sampled = &decision
		default:
			if strings.HasPrefix(lowercaseK, prefixBaggage) {
				result.Baggage[strings.TrimPrefix(lowercaseK, prefixBaggage)] = v
			}
		}
		return nil
	})

	if err != nil {
		return nil, err
	}

	if len(result.SpanID) == 0 && len(result.TraceID) == 0 {
		return nil, opentracing.ErrSpanContextNotFound
	}

	if len(result.SpanID) == 0 || len(result.TraceID) == 0 {
		return nil, opentracing.ErrSpanContextCorrupted
	}
	return result, nil
}

func (p *binaryPropagator) Inject(spanContext opentracing.SpanContext, opaqueCarrier interface{}) error {
	sc, ok := spanContext.(SpanContext)
	if !ok {
		return opentracing.ErrInvalidSpanContext
	}
	carrier, ok := opaqueCarrier.(io.Writer)
	if !ok {
		return opentracing.ErrInvalidCarrier
	}

	state := wire.TracerState{}
	state.TraceId = &sc.TraceID
	state.SpanId = &sc.SpanID
	state.Sampled = sc.Sampled
	state.BaggageItems = sc.Baggage

	b, err := proto.Marshal(&state)
	if err != nil {
		return err
	}

	// Write the length of the marshalled binary to the writer.
	length := uint32(len(b))
	if err := binary.Write(carrier, binary.BigEndian, &length); err != nil {
		return err
	}

	_, err = carrier.Write(b)
	return err
}

func (p *binaryPropagator) Extract(opaqueCarrier interface{}) (opentracing.SpanContext, error) {
	carrier, ok := opaqueCarrier.(io.Reader)
	if !ok {
		return nil, opentracing.ErrInvalidCarrier
	}

	// Read the length of marshalled binary. io.ReadAll isn't that performant
	// since it keeps resizing the underlying buffer as it encounters more bytes
	// to read. By reading the length, we can allocate a fixed sized buf and read
	// the exact amount of bytes into it.
	var length uint32
	if err := binary.Read(carrier, binary.BigEndian, &length); err != nil {
		return nil, opentracing.ErrSpanContextCorrupted
	}
	buf := make([]byte, length)
	if n, err := carrier.Read(buf); err != nil {
		if n > 0 {
			return nil, opentracing.ErrSpanContextCorrupted
		}
		return nil, opentracing.ErrSpanContextNotFound
	}

	ctx := wire.TracerState{}
	if err := proto.Unmarshal(buf, &ctx); err != nil {
		return nil, opentracing.ErrSpanContextCorrupted
	}

	return SpanContext{
		TraceID: *ctx.TraceId,
		SpanID:  *ctx.SpanId,
		Sampled: ctx.Sampled,
		Baggage: ctx.BaggageItems,
	}, nil
}
