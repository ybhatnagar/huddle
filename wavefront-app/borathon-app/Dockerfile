FROM golang AS builder

ADD build-publish/keys/id_rsa /root/.ssh/id_rsa
RUN chmod 600 /root/.ssh/id_rsa
RUN echo "Host gitlab.eng.vmware.com\n\tStrictHostKeyChecking no\n" >> /root/.ssh/config
RUN git config --global url.git@gitlab.eng.vmware.com:.insteadOf https://gitlab.eng.vmware.com/


ADD . /go/src/gitlab.eng.vmware.com/borathon-app

WORKDIR /go/src/gitlab.eng.vmware.com/borathon-app

# install dependencies
RUN go get -u github.com/golang/dep/...
RUN dep ensure -vendor-only -v

# build the app
RUN CGO_ENABLED=0 go build -o /go/bin/borathon-app .

FROM golang:alpine

EXPOSE 8080

COPY --from=builder /go/bin/borathon-app /app/borathon-app

ENTRYPOINT ["/app/borathon-app"]