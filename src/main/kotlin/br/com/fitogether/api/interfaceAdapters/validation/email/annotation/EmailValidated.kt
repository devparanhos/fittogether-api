package br.com.fitogether.api.interfaceAdapters.validation.email.annotation

import br.com.fitogether.api.interfaceAdapters.validation.email.validator.EmailValidatedValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailValidatedValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class EmailValidated(
    val message: String = "O e-mail informado não foi validado. Refaça a validação de código",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
