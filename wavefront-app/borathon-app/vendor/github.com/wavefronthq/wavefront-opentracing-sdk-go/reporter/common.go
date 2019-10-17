package reporter

import (
	"fmt"

	"github.com/opentracing/opentracing-go"
	"github.com/wavefronthq/wavefront-opentracing-sdk-go/tracer"
	wf "github.com/wavefronthq/wavefront-sdk-go/senders"
)

func prepareReferences(span tracer.RawSpan) ([]string, []string) {
	var parents []string
	var followsFrom []string

	for _, ref := range span.References {
		refCtx := ref.ReferencedContext.(tracer.SpanContext)
		switch ref.Type {
		case opentracing.ChildOfRef:
			parents = append(parents, refCtx.SpanID)
		case opentracing.FollowsFromRef:
			followsFrom = append(followsFrom, refCtx.SpanID)
		}
	}
	return parents, followsFrom
}

func prepareTags(span tracer.RawSpan) []wf.SpanTag {
	if len(span.Tags) == 0 {
		return nil
	}
	tags := make([]wf.SpanTag, len(span.Tags))
	i := 0
	for k, v := range span.Tags {
		tags[i] = wf.SpanTag{Key: k, Value: fmt.Sprintf("%v", v)}
		i++
	}
	return tags
}

func prepareLogs(span tracer.RawSpan) []wf.SpanLog {
	if len(span.Logs) == 0 {
		return nil
	}
	logs := make([]wf.SpanLog, len(span.Logs))
	for i, log := range span.Logs {
		fields := make(map[string]string)
		for _, field := range log.Fields {
			fields[field.Key()] = fmt.Sprint(field.Value())
		}
		logs[i] = wf.SpanLog{Timestamp: log.Timestamp.Unix(), Fields: fields}
	}
	return logs
}

func getAppTag(key, defaultVal string, tags map[string]interface{}) (string, bool) {
	if len(tags) > 0 {
		if v, found := tags[key]; found {
			return fmt.Sprint(v), true
		}
	}
	return defaultVal, false
}

func replaceTag(tags map[string]string, key, value string, replace bool) {
	if replace {
		tags[key] = value
	}
}
