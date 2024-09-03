package br.com.fitogether.api.domain.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse(
    @field:JsonProperty("access_token")
    val accessToken: String,

    @field:JsonProperty("user_id")
    val userId: Long,

    @field:JsonProperty("email")
    val email: String,

    @field:JsonProperty("name")
    val name: String
)