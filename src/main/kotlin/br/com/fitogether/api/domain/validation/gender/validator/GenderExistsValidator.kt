package br.com.fitogether.api.domain.validation.gender.validator

import br.com.fitogether.api.domain.service.gender.GenderService
import br.com.fitogether.api.domain.validation.gender.annotation.GenderExists
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class GenderExistsValidator(
    private val genderService: GenderService
) : ConstraintValidator<GenderExists, Long> {
    override fun isValid(id: Long, p1: ConstraintValidatorContext?): Boolean {
        return genderService.findById(id = id)
    }
}