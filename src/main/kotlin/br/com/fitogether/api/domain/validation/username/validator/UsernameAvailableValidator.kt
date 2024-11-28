package br.com.fitogether.api.domain.validation.username.validator

import br.com.fitogether.api.domain.service.user.UserService
import br.com.fitogether.api.domain.validation.username.annotation.UsernameAvailable
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class UsernameAvailableValidator(
    private val userService: UserService
) : ConstraintValidator<UsernameAvailable, String> {
    override fun isValid(username: String, p1: ConstraintValidatorContext?): Boolean {
        return userService.isUsernameAvailable(username = username)
    }
}