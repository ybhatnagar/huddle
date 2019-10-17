package main

import (
	"gitlab.eng.vmware.com/borathon-app/api"
	"gitlab.eng.vmware.com/borathon-app/inits"
	"gitlab.eng.vmware.com/borathon-app/utils"
)

func main() {
	api.RegisterBorathonApi()

	tracerConfig := &utils.TracerConfig{
		Cluster: "Local Cluster",
		CustomTags: map[string]string{
			"abc": "xyz",
		},
	}
	err := utils.InitTracer("borathon-app", "borathon-app-api", "local", tracerConfig)
	if err != nil {
		panic("Tracing Initialization failed...")
	}

	//Initialising server
	inits.EchoWeb.Logger.Fatal(inits.EchoWeb.Start(":" + "8080"))

}
