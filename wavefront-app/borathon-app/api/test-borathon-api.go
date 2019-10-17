package api

import (
	"context"
	"errors"
	"fmt"
	"log"
	"net/http"
	"time"

	"github.com/labstack/echo"
	"github.com/opentracing/opentracing-go"
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
	span := tracer.StartSpan("test-trace-handle")
	defer span.Finish()
	span.SetTag("hello-to", "palash")
	return c.JSON(http.StatusOK, "test")
}

func handleCrossGeo(c echo.Context) (err error) {
	tracer := utils.Tracer
	span := tracer.StartSpan("test-trace-crossgeo")
	defer span.Finish()
	span.SetTag("hello-to", "palash")
	ctx := opentracing.ContextWithSpan(context.Background(), span)
	//Reading query parameters
	serviceUrl := c.QueryParam("serviceUrl")
	if len(serviceUrl) == 0 {
		err := errors.New(fmt.Sprintf("%s query param is not passed or value is empty", serviceUrl))
		return c.String(http.StatusBadRequest, err.Error())
	}

	time.Sleep(2 * time.Second)
	childSpan, _ := opentracing.StartSpanFromContext(ctx, "Service 2")
	time.Sleep(1 * time.Second)
	_, err = http.Get(serviceUrl)
	if err != nil {
		log.Println(err)
	}
	childSpan.Finish()
	return c.JSON(http.StatusOK, "test")
}

func handleIntraGeo(c echo.Context) (err error) {
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
