package br.com.fitogether.api.domain.validation.password.validator

import br.com.fitogether.api.core.extension.isValidPassword
import br.com.fitogether.api.domain.validation.password.annotation.PasswordValid
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordValidValidator : ConstraintValidator<PasswordValid, String> {
    override fun isValid(password: String, p1: ConstraintValidatorContext?): Boolean {
        return password.isValidPassword()
    }

}