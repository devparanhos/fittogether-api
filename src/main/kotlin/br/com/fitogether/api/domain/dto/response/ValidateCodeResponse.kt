package br.com.fitogether.api.domain.dto.response

import br.com.fitogether.api.core.enums.RegistrationStep
import com.fasterxml.jackson.annotation.JsonProperty

data class ValidateCodeResponse(
    @field:JsonProperty("user_id")
    val userId: Long? = null,

    @field:JsonProperty("registration_step")
    val registrationStep: RegistrationStep
)