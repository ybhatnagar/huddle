package tracer

import (
	"strconv"
	"time"
)

// NeverSample basic sampler to not sample any Spans
type NeverSample struct{}

// ShouldSample allways false
func (t NeverSample) ShouldSample(span RawSpan) bool {
	return false
}

// IsEarly will return always true
func (t NeverSample) IsEarly() bool {
	return true
}

// DurationSampler allows spans above a given duration in milliseconds to be reported.
type DurationSampler struct {
	Duration time.Duration
}

// ShouldSample return true if span duration is bigger than Duration
func (t DurationSampler) ShouldSample(span RawSpan) bool {
	return span.Duration > t.Duration
}

// IsEarly will return always false
func (t DurationSampler) IsEarly() bool {
	return false
}

// RateSampler allows spans based on a rate
type RateSampler struct {
	Rate uint64
}

// ShouldSample return true based on a rate
func (t RateSampler) ShouldSample(span RawSpan) bool {
	traceID := span.Context.TraceID[:8]
	id, _ := strconv.ParseUint(traceID, 16, 32)
	return (id % 100) < t.Rate
}

// IsEarly will return always true
func (t RateSampler) IsEarly() bool {
	return true
}
