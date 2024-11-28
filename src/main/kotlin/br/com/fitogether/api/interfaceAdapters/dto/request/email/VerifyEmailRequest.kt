package br.com.fitogether.api.interfaceAdapters.dto.request.email

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

class VerifyEmailRequest(
    @field:NotEmpty(message = "O e-mail não pode ser vazio")
    @field:Email(message = "O e-mail informado não é válido")
    val email: String
)