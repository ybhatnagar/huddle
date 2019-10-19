package api

import (
	"log"
	"net/http"
	"os"
	"strconv"
	"time"

	"github.com/labstack/echo"
	"github.com/opentracing/opentracing-go"
	"github.com/opentracing/opentracing-go/ext"
	"gitlab.eng.vmware.com/borathon-app/inits"
	"gitlab.eng.vmware.com/borathon-app/utils"
)

// Registers all build APIs
func RegisterBorathonApi() {
	inits.EchoWeb.GET("/borathonapi/test", handle)
	inits.EchoWeb.GET("/borathonapi/testcrossgeo", handleCrossGeo)

}

func handle(c echo.Context) (err error) {
	tracer := utils.Tracer
	var serverSpan opentracing.Span
	appSpecificOperationName := os.Getenv("app") + "-get"
	wireContext, err := tracer.Extract(
		opentracing.HTTPHeaders,
		opentracing.HTTPHeadersCarrier(c.Request().Header))
	if err != nil {
		log.Println(err)
	}
	// Create the span referring to the RPC client if available.
	// If wireContdockeext == nil, a root span will be created.
	serverSpan = opentracing.StartSpan(
		appSpecificOperationName,
		ext.RPCServerOption(wireContext))

	defer serverSpan.Finish()
	return c.JSON(http.StatusOK, os.Getenv("app"))
}

func handleCrossGeo(c echo.Context) (err error) {
	tracer := utils.Tracer
	span := tracer.StartSpan("trace-crossgeo")
	defer span.Finish()
	span.SetTag("service", os.Getenv("app"))

	//Reading query parameters
	serviceUrl := c.QueryParam("serviceUrl")
	sourceDelay := c.QueryParam("sourceDelay")
	destinationDelay := c.QueryParam("destinationDelay")
	destinationUrl := c.QueryParam("destinationUrl")
	sourceDelayInt, err := strconv.Atoi(sourceDelay)

	if err != nil {
		log.Println(err)
	}
	time.Sleep(time.Duration(sourceDelayInt) * time.Millisecond)

	httpClient := &http.Client{}
	httpReq, _ := http.NewRequest("GET", serviceUrl, nil)
	q := httpReq.URL.Query()
	if destinationDelay != "" && destinationUrl != "" {
		q.Add("sourceDelay", destinationDelay)
		q.Add("serviceUrl", destinationUrl)
	}
	tracer.Inject(
		span.Context(),
		opentracing.HTTPHeaders,
		opentracing.HTTPHeadersCarrier(httpReq.Header))

	resp, err := httpClient.Do(httpReq)
	if err != nil {
		return c.String(http.StatusBadRequest, err.Error())
	}
	return c.JSON(http.StatusOK, resp)
}
