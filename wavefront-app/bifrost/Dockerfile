FROM golang AS builder


ADD . /go/src/gitlab.eng.vmware.com/bifrost

WORKDIR /go/src/gitlab.eng.vmware.com/bifrost

# install dependencies
RUN go get -u github.com/golang/dep/...
RUN dep ensure -vendor-only -v

# build the app
RUN CGO_ENABLED=0 go build -o /go/bin/bifrost .

FROM golang:alpine

EXPOSE 8080

COPY --from=builder /go/bin/bifrost /app/bifrost

ENTRYPOINT ["/app/bifrost"]