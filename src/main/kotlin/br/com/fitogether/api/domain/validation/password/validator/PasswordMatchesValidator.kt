package br.com.fitogether.api.domain.validation.password.validator

import br.com.fitogether.api.domain.model.request.user.CreateUserRequest
import br.com.fitogether.api.domain.validation.password.annotation.PasswordMatches
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordMatchesValidator : ConstraintValidator<PasswordMatches?, CreateUserRequest> {

    private var messageTemplate: String? = null

    override fun initialize(constraintAnnotation: PasswordMatches?) {
        messageTemplate = constraintAnnotation?.message
    }

    override fun isValid(user: CreateUserRequest, context: ConstraintValidatorContext): Boolean {
        if (user.confirmPassword == user.password) {
            return true
        } else {
            context.buildConstraintViolationWithTemplate(
                messageTemplate.toString()
            ).addPropertyNode("confirmPassword").addConstraintViolation()

            return false
        }
    }
}