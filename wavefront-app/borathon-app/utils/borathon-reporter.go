package utils

import (
	"os"

	"github.com/opentracing/opentracing-go"
	"github.com/wavefronthq/wavefront-opentracing-sdk-go/reporter"
	wfTracer "github.com/wavefronthq/wavefront-opentracing-sdk-go/tracer"
	wfApplication "github.com/wavefronthq/wavefront-sdk-go/application"
	"github.com/wavefronthq/wavefront-sdk-go/senders"
)

// TracerConfig can be used to define config other than application,service and source while creating
// tracer object
type TracerConfig struct {
	Cluster    string
	Shard      string
	CustomTags map[string]string
}

var Tracer opentracing.Tracer

// InitTracer initialize tracer object, initialize the GlobalTracer with this newly created
// tracer object
// Call utils.Tracer to access the tracer object
func InitTracer(application string, service string, source string, tracerConfig *TracerConfig) error {
	directCfg := &senders.DirectConfiguration{
		Server:               "https://symphony.wavefront.com",
		Token:                os.Getenv("Token"),
		BatchSize:            40000,
		MaxBufferSize:        50000,
		FlushIntervalSeconds: 1,
	}
	sender, err := senders.NewDirectSender(directCfg)
	if err != nil {
		return err
	}
	appTags := wfApplication.New(application, service)
	if tracerConfig != nil {
		if len(tracerConfig.Cluster) > 0 {
			appTags.Cluster = tracerConfig.Cluster
		}
		if len(tracerConfig.Shard) > 0 {
			appTags.Shard = tracerConfig.Shard
		}
		if tracerConfig.CustomTags != nil && len(tracerConfig.CustomTags) > 0 {
			for k, v := range tracerConfig.CustomTags {
				appTags.CustomTags[k] = v
			}
		}
	}
	wfReporter := reporter.New(sender, appTags, reporter.Source(source))
	clReporter := reporter.NewConsoleSpanReporter(source)
	reporter := reporter.NewCompositeSpanReporter(wfReporter, clReporter)
	Tracer = wfTracer.New(reporter)
	opentracing.InitGlobalTracer(Tracer)
	return nil
}
