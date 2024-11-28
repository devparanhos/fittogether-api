package br.com.fitogether.api.domain.validation.gender.annotation

import br.com.fitogether.api.domain.validation.gender.validator.GenderExistsValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [GenderExistsValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class GenderExists(
    val message: String = "Gênero informado é inválido",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
