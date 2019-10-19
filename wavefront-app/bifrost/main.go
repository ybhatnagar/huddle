package main

import (
	"fmt"
	"log"
	"net/http"
)

func main() {

	pingApp()

	for {

	}

}

func pingApp() {
	for i := 0; i < 1; i++ {
		httpClient := &http.Client{}
		httpReq, _ := http.NewRequest("GET", "http://google.com", nil)
		q := httpReq.URL.Query()
		q.Add("sourceDelay", "42")
		q.Add("destinationDelay", "22")
		q.Add("serviceUrl","")
		q.Add("destinationUrl","")
		httpReq.URL.RawQuery = q.Encode()

		fmt.Println(httpReq.URL.String())

		resp, err := httpClient.Do(httpReq)
		if err != nil {
			log.Println(err)
		}
		log.Println(resp)
	}
}
