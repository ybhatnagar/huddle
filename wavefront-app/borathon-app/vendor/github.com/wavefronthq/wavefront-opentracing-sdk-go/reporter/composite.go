package reporter

import (
	"fmt"

	"github.com/wavefronthq/wavefront-opentracing-sdk-go/tracer"
)

// CompositeSpanReporter reports spans to multiple SpanReporter's.
type CompositeSpanReporter struct {
	reporters []tracer.SpanReporter
}

// NewCompositeSpanReporter returns a SpanReporter with multiple sub reporters.
func NewCompositeSpanReporter(reporters ...tracer.SpanReporter) tracer.SpanReporter {
	return CompositeSpanReporter{reporters: reporters}
}

// ReportSpan complies with the `tracer.SpanReporter` interface.
func (c CompositeSpanReporter) ReportSpan(span tracer.RawSpan) {
	for _, reporter := range c.reporters {
		reporter.ReportSpan(span)
	}
}

func (c CompositeSpanReporter) Close() error {
	errStr := ""
	for _, reporter := range c.reporters {
		err := reporter.Close()
		if err != nil {
			errStr = errStr + err.Error() + "\n"
		}
	}
	if errStr != "" {
		return fmt.Errorf(errStr)
	}
	return nil
}
