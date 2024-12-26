package br.com.fitogether.api.domain.dto.request.password_reset_token

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

data class ForgotPasswordRequest(
    @field:NotNull(message = "Você precisa informar um gênero")
    @field:Email(message = "Email inválido")
    val email: String
)
