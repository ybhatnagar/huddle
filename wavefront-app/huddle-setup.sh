#!/bin/bash

echo "Deploying latest releases"
echo "-------------------------"
cd borathon-app/deploy/
helm install --namespace=default ./cortex/ --name cortex
helm install --namespace=default ./cost-analysis/ --name cost-analysis
helm install --namespace=default ./heimdall/ --name heimdall
helm install --namespace=default ./lemans-gateway/ --name lemans-gateway
helm install --namespace=default ./lemans-resources/ --name lemans-resources
helm install --namespace=default ./lint-app/ --name lint-app
helm install --namespace=default ./lint-ui/ --name lint-ui
helm install --namespace=default ./stargate-deployment/ --name stargate-deployment
echo "Completed"
echo "---------------------"
echo " Deploy Bifrost Now"
echo "---------------------"