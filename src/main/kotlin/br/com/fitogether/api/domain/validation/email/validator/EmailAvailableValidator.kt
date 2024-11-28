package br.com.fitogether.api.domain.validation.email.validator

import br.com.fitogether.api.domain.service.user.UserService
import br.com.fitogether.api.domain.validation.email.annotation.EmailAvailable
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired

class EmailAvailableValidator(
    @Autowired private val userService: UserService
) : ConstraintValidator<EmailAvailable, String> {
    override fun isValid(data: String, p1: ConstraintValidatorContext?): Boolean {
        return userService.isEmailAvailable(email = data)
    }
}