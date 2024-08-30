package br.com.fitogether.api.domain.model.request.user

import br.com.fitogether.api.domain.validation.email.annotation.EmailAvailable
import br.com.fitogether.api.domain.validation.email.annotation.EmailValidated
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length
import java.util.Date

data class CreateUserRequest(
    @field:Email(message = "O e-mail informado não é válido")
    @field:NotEmpty(message = "O e-mail não pode ser vazio")
    @EmailAvailable
    @EmailValidated
    val email: String,

    @field:NotBlank(message = "O nome é obrigatório")
    @field:Length(min = 3, message = "O nome deve conter pelo 3 caracteres")
    val name: String,

    val cellphone: String,

    @JsonAlias("birth_date")
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    val birthDate: Date,

    val password: String,

    @JsonAlias("confirm_password")
    val confirmPassword: String,
)
