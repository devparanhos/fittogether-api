package br.com.fitogether.api.interfaceAdapters.dto.response.email

import br.com.fitogether.api.core.enums.UserRegistrationStatus
import com.fasterxml.jackson.annotation.JsonProperty

data class VerifyEmailResponse(
    @field:JsonProperty("status")
    val status: UserRegistrationStatus,

    @field:JsonProperty("sending_code")
    val sendingCode: Boolean,

    @field:JsonProperty("message")
    val message: String? = null
)