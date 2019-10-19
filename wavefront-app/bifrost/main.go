package main

import (
	"log"
	"net/http"
	"time"

	"github.com/opentracing/opentracing-go"
	"gitlab.eng.vmware.com/bifrost/utils"
)

func main() {
	// api.RegisterBorathonApi()

	tracerConfig := &utils.TracerConfig{
		Cluster: "Local Cluster",
		CustomTags: map[string]string{
			"source.service":      "bifrost",
			"destination.service": "borathon-app",
		},
	}
	err := utils.InitTracer("bifrost", "bifrost-api", "local", tracerConfig)
	if err != nil {
		panic("Tracing Initialization failed...")
	}

	// //Initialising server
	// inits.EchoWeb.Logger.Fatal(inits.EchoWeb.Start(":" + "1323"))

	pingApp()

	for {

	}

}

func pingApp() {
	for i := 0; i < 200; i++ {
		tracer := utils.Tracer
		span := tracer.StartSpan("test-trace-cross-service")
		defer span.Finish()
		span.SetTag("service-1", "bifrost-get")
		time.Sleep(1 * time.Second)

		httpClient := &http.Client{}
		httpReq, _ := http.NewRequest("GET", "http://borathon-app-0.borathon-app.default.svc.cluster.local:8080/borathonapi/test", nil)

		tracer.Inject(
			span.Context(),
			opentracing.HTTPHeaders,
			opentracing.HTTPHeadersCarrier(httpReq.Header))
		resp, err := httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}
		log.Println(resp)
	}
}
