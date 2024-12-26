package br.com.fitogether.api.controller.user

import br.com.fitogether.api.domain.dto.request.authentication.LoginRequest
import br.com.fitogether.api.domain.dto.request.password_reset_token.PasswordResetRequest
import br.com.fitogether.api.domain.dto.request.password_reset_token.ForgotPasswordRequest
import br.com.fitogether.api.domain.dto.request.user.CreateUserRequest
import br.com.fitogether.api.domain.dto.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.dto.response.ValidateEmailResponse
import br.com.fitogether.api.domain.dto.request.user.ValidateEmailRequest
import br.com.fitogether.api.domain.dto.response.AuthenticationResponse
import br.com.fitogether.api.domain.dto.response.MessageResponse
import br.com.fitogether.api.domain.dto.response.ValidateCodeResponse
import br.com.fitogether.api.domain.service.user.UserService

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(
    private val userService: UserService,
) {
    @PostMapping(value = ["validate-email"])
    fun validateEmail(
        @RequestBody @Valid request: ValidateEmailRequest
    ): ValidateEmailResponse {
        return userService.validateEmail(request = request)
    }

    @PostMapping(value = ["validate-code"])
    fun validateCode(@RequestBody @Valid request: ValidateCodeRequest): ValidateCodeResponse {
        return userService.validateCode(request = request)
    }

    @PostMapping
    fun create(@RequestBody @Valid request: CreateUserRequest): AuthenticationResponse {
        return userService.createUser(request = request)
    }

    @PostMapping(value = ["login"])
    fun login(@RequestBody login: LoginRequest): AuthenticationResponse {
        return userService.authenticate(login = login)
    }

    @PostMapping(value = ["/forgot-password"])
    fun forgotPassword(@RequestBody @Valid request: ForgotPasswordRequest): ResponseEntity<MessageResponse> {
        userService.forgotPassword(request.email)
        return ResponseEntity.ok(MessageResponse("Link de redefinição de senha enviado"))
    }

    @PostMapping(value = ["/password-reset"])
    fun passwordReset(@RequestBody @Valid request: PasswordResetRequest): ResponseEntity<MessageResponse> {
        userService.passwordReset(request.token, request.password)
        return ResponseEntity.ok(MessageResponse("Senha redefinida com sucesso"))
    }
}