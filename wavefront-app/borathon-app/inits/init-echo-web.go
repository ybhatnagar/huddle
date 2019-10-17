package inits

import (
	"github.com/labstack/echo"
	"github.com/labstack/echo/middleware"
	"gopkg.in/go-playground/validator.v9"
)

// Variable holding shared Echo
var EchoWeb *echo.Echo

// Initialize shared Echo
func init() {
	// Initialize Echo
	EchoWeb = echo.New()

	// Initialize validator
	EchoWeb.Validator = &BorathonValidator{validator: validator.New()}

	// Log all API calls
	EchoWeb.Use(middleware.Logger())
}
