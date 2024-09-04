package br.com.fitogether.api.domain.dto.request.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ValidateCodeRequest(
    @field:NotEmpty(message = "O e-mail não pode ser vazio")
    @field:Email(message = "O e-mail informado não é válido")
    val email: String,

    @Pattern(regexp = "\\d+", message = "O código deve conter apenas números.")
    @field:NotEmpty(message = "O código não pode ser vazio")
    @field:Size(min = 6, max = 6, message = "O código deve conter 6 digitos")
    val code: String
)