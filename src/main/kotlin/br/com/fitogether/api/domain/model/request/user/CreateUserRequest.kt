package br.com.fitogether.api.domain.model.request.user

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date

data class CreateUserRequest(
    val email: String,
    val name: String,
    val cellphone: String,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    val birthDate: Date,
    val password: String
)
