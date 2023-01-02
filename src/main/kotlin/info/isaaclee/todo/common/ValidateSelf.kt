package info.isaaclee.todo.common

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator

abstract class ValidateSelf<T> {
	private val validator: Validator
	constructor() {
		val factory = Validation.buildDefaultValidatorFactory()
		this.validator = factory.validator
	}

	protected fun validateSelf() {
		val violations: Set<ConstraintViolation<T>> = this.validator.validate(this as T)
		if (violations.isNotEmpty()) {
			throw ConstraintViolationException(violations)
		}
	}
}
