package application

import (
	"log"
	"time"

	"github.com/wavefronthq/wavefront-sdk-go/senders"
)

// HeartbeatService sends a heartbeat metric every 5 minutes
type HeartbeatService interface {
	Close()
}

type heartbeater struct {
	sender      senders.Sender
	application Tags
	source      string
	components  []string

	ticker *time.Ticker
	stop   chan struct{}
}

// StartHeartbeatService will create and start a new HeartbeatService
func StartHeartbeatService(sender senders.Sender, application Tags, source string, components ...string) HeartbeatService {
	hb := &heartbeater{
		sender:      sender,
		application: application,
		source:      source,
		components:  components,
		ticker:      time.NewTicker(5 * time.Minute),
		stop:        make(chan struct{}),
	}

	go func() {
		for {
			select {
			case <-hb.ticker.C:
				hb.beat()
			case <-hb.stop:
				return
			}
		}
	}()

	hb.beat()
	return hb
}

func (hb *heartbeater) Close() {
	hb.ticker.Stop()
	hb.stop <- struct{}{} // block until goroutine exits
}

func (hb *heartbeater) beat() {
	tags := hb.application.Map()
	tags["component"] = "wavefront-generated"
	hb.send(tags)

	for _, component := range hb.components {
		tags["component"] = component
		hb.send(tags)
	}
}

func (hb *heartbeater) send(tags map[string]string) {
	err := hb.sender.SendMetric("~component.heartbeat", 1, 0, hb.source, tags)
	if err != nil {
		log.Printf("heartbeater SendMetric error: %v\n", err)
	}
}
