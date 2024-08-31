package br.com.fitogether.api.domain.validation.date.annotation

import br.com.fitogether.api.domain.validation.date.validator.BirthdateValidValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [BirthdateValidValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class BirthdateValid(
    val message: String = "Data de nascimento inválida",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
