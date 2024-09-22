package br.com.fitogether.api.interfaceAdapters.validation.date.validator

import br.com.fitogether.api.core.extension.isAtLeast16YearsOld
import br.com.fitogether.api.interfaceAdapters.validation.date.annotation.AgePermitted
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class AgePermittedValidator : ConstraintValidator<AgePermitted?, String> {
    override fun isValid(date: String, p1: ConstraintValidatorContext?): Boolean {
        return date.isAtLeast16YearsOld()
    }
}