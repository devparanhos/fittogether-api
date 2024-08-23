package br.com.fitogether.api.domain.model.response

import br.com.fitogether.api.core.enums.UserRegistrationStatus

data class ValidateEmailResponse(
    val status: UserRegistrationStatus,
    val sendingCode: Boolean,
    val message: String? = null
)