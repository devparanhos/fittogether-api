package br.com.fitogether.api.domain.dto.request.authentication

data class LoginRequest(
    val email: String,
    val password: String
)
