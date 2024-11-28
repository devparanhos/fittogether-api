package br.com.fitogether.api.interfaceAdapters.validation.password.annotation

import br.com.fitogether.api.interfaceAdapters.validation.password.validator.PasswordMatchesValidator
import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = [br.com.fitogether.api.interfaceAdapters.validation.password.validator.PasswordMatchesValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class PasswordMatches(
    val message: String = "A confirmação de senha precisa ser igual a senha",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
