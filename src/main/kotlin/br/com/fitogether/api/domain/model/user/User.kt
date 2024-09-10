package br.com.fitogether.api.domain.model.user

import br.com.fitogether.api.core.enums.RegistrationStep

data class User(
    val id: Long?,
    val email: String,
    val name: String,
    val registrationStep: RegistrationStep,
    val username: String,
    val gender: String?
)