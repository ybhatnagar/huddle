#!/bin/bash

echo "Deleting existing releases"
echo "--------------------------"
helm delete cortex --purge
helm delete cost-analysis --purge
helm delete heimdall --purge
helm delete lemans-gateway --purge
helm delete lemans-resources --purge
helm delete lint-app --purge
helm delete lint-ui --purge
helm delete stargate-deployment --purge
echo "--------------------------"
echo "Completed"
