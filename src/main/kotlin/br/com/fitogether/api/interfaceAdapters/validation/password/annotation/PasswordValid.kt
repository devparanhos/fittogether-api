package br.com.fitogether.api.interfaceAdapters.validation.password.annotation

import br.com.fitogether.api.interfaceAdapters.validation.password.validator.PasswordValidValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [PasswordValidValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class PasswordValid(
    val message: String = "A senha informada é inválida. Precisa conter pelo menos 1 letra minúscula, 1 letra maiúscula, 1 número e 1 caracter especial",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)
