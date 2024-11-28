package br.com.fitogether.api.interfaceAdapters.validation.date.validator

import br.com.fitogether.api.core.extension.isValidDate
import br.com.fitogether.api.interfaceAdapters.validation.date.annotation.BirthdateValid
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class BirthdateValidValidator : ConstraintValidator<BirthdateValid, String> {
    override fun isValid(birthdate: String, p1: ConstraintValidatorContext?): Boolean {
        return birthdate.isValidDate()
    }
}