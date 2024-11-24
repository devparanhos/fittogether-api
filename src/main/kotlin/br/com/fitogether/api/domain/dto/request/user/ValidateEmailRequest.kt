package br.com.fitogether.api.domain.dto.request.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

class ValidateEmailRequest(
    @field:NotBlank(message = "O campo email é obrigatório.")
    @field:Email(message = "O e-mail informado é inválido.")
    val email: String
)