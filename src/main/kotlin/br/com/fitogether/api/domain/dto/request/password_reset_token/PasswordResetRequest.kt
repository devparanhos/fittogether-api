package br.com.fitogether.api.domain.dto.request.password_reset_token

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class PasswordResetRequest(
    @field:NotNull(message = "Token é obrigatório")
    val token: String,

    @field:NotNull(message = "Nova senha é obrigatória")
    @field:Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    val newPassword: String
)
