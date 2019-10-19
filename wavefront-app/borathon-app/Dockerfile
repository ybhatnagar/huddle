FROM golang AS builder

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