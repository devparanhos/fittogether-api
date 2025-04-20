package br.com.fitogether.api.domain.dto.request.user

import br.com.fitogether.api.domain.validation.date.annotation.AgePermitted
import br.com.fitogether.api.domain.validation.date.annotation.BirthdateValid
import br.com.fitogether.api.domain.validation.email.annotation.EmailAvailable
import br.com.fitogether.api.domain.validation.email.annotation.EmailValidated
import br.com.fitogether.api.domain.validation.password.annotation.PasswordMatches
import br.com.fitogether.api.domain.validation.password.annotation.PasswordValid
import br.com.fitogether.api.domain.validation.username.annotation.UsernameAvailable
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length

@PasswordMatches
data class CreateUserRequest(
    @field:Email(message = "O e-mail informado não é válido")
    @field:NotEmpty(message = "O e-mail não pode ser vazio")
    @EmailAvailable
    @EmailValidated
    val email: String,

    @field:NotBlank(message = "O nome de usuário é obrigatório")
    @field:Length(min = 3, message = "O nome de usuário deve conter pelo 3 caracteres")
    @UsernameAvailable
    var username: String,

    @field:NotBlank(message = "O nome é obrigatório")
    @field:Length(min = 3, message = "O nome deve conter pelo 3 caracteres")
    val name: String,

    @JsonAlias("birth_date")
    @field:NotBlank(message = "A data de nascimento é obrigatória")
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @BirthdateValid
    @AgePermitted
    val birthDate: String,

    @field:NotBlank(message = "A senha é obrigatória")
    @field:Length(min = 6, message = "A senha deve conter pelo 6 caracteres")
    @PasswordValid
    val password: String,

    @JsonAlias("confirm_password")
    val confirmPassword: String,
)
