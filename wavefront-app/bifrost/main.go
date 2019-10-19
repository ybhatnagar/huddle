package main

import (
	"log"
	"net/http"
	"time"
)

func main() {

	pingApp()

	for {

	}
}

func pingApp() {
	for {
		time.Sleep(500 * time.Millisecond)
		httpClient := &http.Client{}
		httpReq, _ := http.NewRequest("GET", "http://cortex-0.cortex.default.svc.cluster.local:8080/borathonapi/testcrossgeo", nil)
		q := httpReq.URL.Query()
		q.Add("sourceDelay", "2")
		q.Add("destinationDelay", "1")
		q.Add("serviceUrl", "http://heimdall-0.heimdall.default.svc.cluster.local:8080/borathonapi/testcrossgeo")
		q.Add("destinationUrl", "http://lemans-resources-0.lemans-resources.default.svc.cluster.local:8080/borathonapi/test")
		httpReq.URL.RawQuery = q.Encode()
		_, err := httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}

		httpClient = &http.Client{}
		httpReq, _ = http.NewRequest("GET", "http://lint-app-0.lint-app.default.svc.cluster.local:8080/borathonapi/testcrossgeo", nil)
		q = httpReq.URL.Query()
		q.Add("sourceDelay", "22")
		q.Add("destinationDelay", "5")
		q.Add("serviceUrl", "http://heimdall-1.heimdall.default.svc.cluster.local:8080/borathonapi/testcrossgeo")
		q.Add("destinationUrl", "http://stargate-deployment-0.stargate-deployment.default.svc.cluster.local:8080/borathonapi/test")
		httpReq.URL.RawQuery = q.Encode()
		_, err = httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}

		httpClient = &http.Client{}
		httpReq, _ = http.NewRequest("GET", "http://cost-analysis-0.cost-analysis.default.svc.cluster.local:8080/borathonapi/testcrossgeo", nil)
		q = httpReq.URL.Query()
		q.Add("sourceDelay", "3")
		q.Add("destinationDelay", "")
		q.Add("serviceUrl", "http://heimdall-1.heimdall.default.svc.cluster.local:8080/borathonapi/test")
		q.Add("destinationUrl", "")
		httpReq.URL.RawQuery = q.Encode()
		_, err = httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}

		httpClient = &http.Client{}
		httpReq, _ = http.NewRequest("GET", "http://stargate-deployment-0.stargate-deployment.default.svc.cluster.local:8080/borathonapi/testcrossgeo", nil)
		q = httpReq.URL.Query()
		q.Add("sourceDelay", "20")
		q.Add("destinationDelay", "")
		q.Add("serviceUrl", "http://cortex-0.cortex.default.svc.cluster.local:8080/borathonapi/test")
		q.Add("destinationUrl", "")
		httpReq.URL.RawQuery = q.Encode()
		_, err = httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}

		httpClient = &http.Client{}
		httpReq, _ = http.NewRequest("GET", "http://lemans-gateway-0.lemans-gateway.default.svc.cluster.local:8080/borathonapi/testcrossgeo", nil)
		q = httpReq.URL.Query()
		q.Add("sourceDelay", "17")
		q.Add("destinationDelay", "")
		q.Add("serviceUrl", "http://lemans-resources-0.lemans-resources.default.svc.cluster.local:8080/borathonapi/test")
		q.Add("destinationUrl", "")
		httpReq.URL.RawQuery = q.Encode()
		_, err = httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}

		httpClient = &http.Client{}
		httpReq, _ = http.NewRequest("GET", "http://lint-app-0.lint-app.default.svc.cluster.local:8080/borathonapi/testcrossgeo", nil)
		q = httpReq.URL.Query()
		q.Add("sourceDelay", "2")
		q.Add("destinationDelay", "")
		q.Add("serviceUrl", "http://lint-ui-0.lint-ui.default.svc.cluster.local:8080/borathonapi/test")
		q.Add("destinationUrl", "")
		httpReq.URL.RawQuery = q.Encode()
		_, err = httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}

		httpClient = &http.Client{}
		httpReq, _ = http.NewRequest("GET", "http://lint-app-0.lint-app.default.svc.cluster.local:8080/borathonapi/testcrossgeo", nil)
		q = httpReq.URL.Query()
		q.Add("sourceDelay", "12")
		q.Add("destinationDelay", "")
		q.Add("serviceUrl", "http://lemans-gateway-0.lemans-gateway.default.svc.cluster.local:8080/borathonapi/test")
		q.Add("destinationUrl", "")
		httpReq.URL.RawQuery = q.Encode()
		_, err = httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}

		httpClient = &http.Client{}
		httpReq, _ = http.NewRequest("GET", "http://stargate-deployment-0.stargate-deployment.default.svc.cluster.local:8080/borathonapi/testcrossgeo", nil)
		q = httpReq.URL.Query()
		q.Add("sourceDelay", "42")
		q.Add("destinationDelay", "")
		q.Add("serviceUrl", "http://lemans-gateway-0.lemans-gateway.default.svc.cluster.local:8080/borathonapi/test")
		q.Add("destinationUrl", "")
		httpReq.URL.RawQuery = q.Encode()
		_, err = httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}

	}
}
