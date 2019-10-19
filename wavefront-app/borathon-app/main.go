package main

import (
	"os"

	"gitlab.eng.vmware.com/borathon-app/api"
	"gitlab.eng.vmware.com/borathon-app/inits"
	"gitlab.eng.vmware.com/borathon-app/utils"
)

func main() {
	api.RegisterBorathonApi()

	tracerConfig := &utils.TracerConfig{
		Cluster: "Local Cluster",
		CustomTags: map[string]string{
			"app": os.Getenv("app"),
		},
	}
	err := utils.InitTracer(os.Getenv("app"), os.Getenv("app"), "local", tracerConfig)
	if err != nil {
		panic("Tracing Initialization failed...")
	}

	//Initialising server
	inits.EchoWeb.Logger.Fatal(inits.EchoWeb.Start(":" + "8080"))

}
