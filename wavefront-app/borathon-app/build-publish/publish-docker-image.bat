@echo off
SET VERSION=%1

:: Validate no arguments input
if [%1]==[] goto noArgumentsError

SET LABEL="borathon-app"
SET REPO_PATH="cmbu-cicd-docker.artifactory.eng.vmware.com"
SET REPO_USERNAME="cmbu-cicd-deployer"
SET REPO_PASSWORD="VMware1!"
SET TAG="%LABEL%:%VERSION%"

:: Login to Artifactory docker repo
docker login %REPO_PATH% --username %REPO_USERNAME% --password %REPO_PASSWORD%

:: Build the image
docker build --label %LABEL% --tag %REPO_PATH%/%TAG% ..\.

:: Push the image
docker push %REPO_PATH%/%TAG%

:: Remove the local image
docker rmi %REPO_PATH%/%TAG%

:: End the execution
@echo Done
goto :eof

:noArgumentsError
@echo "No arguments passed. Image version number is expected"
exit 1