package br.com.fitogether.api.domain.validation.email.validator

import br.com.fitogether.api.domain.service.code.ValidationCodeService
import br.com.fitogether.api.domain.validation.email.annotation.EmailValidated
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired

class EmailValidatedValidator(
    @Autowired private val validationCodeService: ValidationCodeService
) : ConstraintValidator<EmailValidated, String> {
    override fun isValid(email: String, p1: ConstraintValidatorContext?): Boolean {
        return validationCodeService.isEmailValidated(email = email)
    }
}