package inits

import (
	"gopkg.in/go-playground/validator.v9"
)

// Struct for Validator
type BorathonValidator struct {
	validator *validator.Validate
}

func (div *BorathonValidator) Validate(i interface{}) error {
	return div.validator.Struct(i)
}
