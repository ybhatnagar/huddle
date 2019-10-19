package api

import (
	"errors"
	"fmt"
	"log"
	"net/http"
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
	inits.EchoWeb.GET("/borathonapi/testintrageo", handleIntraGeo)

}

func handle(c echo.Context) (err error) {
	tracer := utils.Tracer
	var serverSpan opentracing.Span
	appSpecificOperationName := "borathon-app-get"
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
	time.Sleep(1 * time.Second)
	return c.JSON(http.StatusOK, "Test")
}

func handleCrossGeo(c echo.Context) (err error) {
	tracer := utils.Tracer
	span := tracer.StartSpan("test-trace-crossgeo")
	defer span.Finish()
	span.SetTag("service-1", "heimdall")

	//Reading query parameters
	serviceUrl := c.QueryParam("serviceUrl")
	time.Sleep(2 * time.Second)

	httpClient := &http.Client{}
	httpReq, _ := http.NewRequest("GET", serviceUrl, nil)

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

func handleIntraGeo(c echo.Context) (err error) {
	tracer := utils.Tracer
	span := tracer.StartSpan("test-trace-intrageo")
	defer span.Finish()
	span.SetTag("hello-to", "palash")
	//Reading query parameters
	serviceUrl := c.QueryParam("serviceUrl")
	if len(serviceUrl) == 0 {
		err := errors.New(fmt.Sprintf("%s query param is not passed or value is empty", serviceUrl))
		return c.String(http.StatusBadRequest, err.Error())
	}
	_, err = http.Get(serviceUrl)
	if err != nil {
		log.Println(err)
	}
	return c.JSON(http.StatusOK, "test")
}
