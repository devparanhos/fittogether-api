package br.com.fitogether.api.domain.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse(
    @field:JsonProperty("access_token")
    val accessToken: String,
)