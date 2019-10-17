package tracer

import (
	"math/rand"
	"sync"
	"time"

	"github.com/tuvistavie/securerandom"
)

const (
	defaultComponent = "none"
)

var (
	seededIDGen = rand.New(rand.NewSource(time.Now().UnixNano()))
	// The golang rand generators are *not* intrinsically thread-safe.
	seededIDLock sync.Mutex
)

// TODO: error control
func randomID() string {
	a, _ := securerandom.Uuid()
	return a
}

// TODO: error control
func randomID2() (string, string) {
	a, _ := securerandom.Uuid()
	b, _ := securerandom.Uuid()
	return a, b
}
