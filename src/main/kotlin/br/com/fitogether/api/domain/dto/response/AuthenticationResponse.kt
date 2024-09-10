package br.com.fitogether.api.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthenticationResponse(
    @field:JsonProperty("access_token")
    val accessToken: String?,

    @field:JsonProperty("user")
    val user: UserResponse
)