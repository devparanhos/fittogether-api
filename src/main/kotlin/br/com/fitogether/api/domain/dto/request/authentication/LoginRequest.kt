package br.com.fitogether.api.domain.model.request.authentication

data class LoginRequest(
    val email: String,
    val password: String
)
