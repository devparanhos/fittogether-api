package br.com.fitogether.api.interfaceAdapters.validation.date.annotation

import br.com.fitogether.api.interfaceAdapters.validation.date.validator.AgePermittedValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [AgePermittedValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class AgePermitted(
    val message: String = "Você precisa ter pelo menos 16 anos",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
