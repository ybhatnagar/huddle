#!/bin/bash
VERSION=$1

#Validate no arguments input
if [ $# -eq 0 ]
  then
    echo "No arguments passed. Image version number is expected"
	exit 1
fi

if [ -z "$HOME" ]
then
      echo "\$HOME is empty"
      exit 1
else
      echo "\$HOME is set to $HOME"
fi

mkdir keys
cp $HOME/.ssh/id_rsa ./keys

LABEL="borathon-app"
REPO_PATH="cmbu-cicd-docker.artifactory.eng.vmware.com"
REPO_USERNAME="cmbu-cicd-deployer"
REPO_PASSWORD="VMware1!"
TAG="$LABEL:$VERSION"

# Login to Artifactory docker repo
docker login $REPO_PATH --username $REPO_USERNAME  --password $REPO_PASSWORD

# Build the image
docker build --label $LABEL --tag $REPO_PATH/$TAG ../.

# Push the image
docker push $REPO_PATH/$TAG

# Remove the local image
docker rmi $REPO_PATH/$TAG