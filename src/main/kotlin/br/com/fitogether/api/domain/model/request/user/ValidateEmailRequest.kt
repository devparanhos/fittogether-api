package br.com.fitogether.api.domain.model.request.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

class ValidateEmailRequest(
    @field:NotEmpty(message = "O e-mail não pode ser vazio")
    @field:Email(message = "O e-mail informado não é válido")
    val email: String
)