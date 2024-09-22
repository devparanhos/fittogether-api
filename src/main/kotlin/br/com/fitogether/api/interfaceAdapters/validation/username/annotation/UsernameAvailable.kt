package br.com.fitogether.api.interfaceAdapters.validation.username.annotation

import br.com.fitogether.api.interfaceAdapters.validation.username.validator.UsernameAvailableValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [UsernameAvailableValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class UsernameAvailable(
    val message: String = "O nome de usuário não está disponível",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
