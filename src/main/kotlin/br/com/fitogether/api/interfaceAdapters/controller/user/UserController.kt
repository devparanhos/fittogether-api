package br.com.fitogether.api.interfaceAdapters.controller.user

import br.com.fitogether.api.application.service.AuthenticationService
import br.com.fitogether.api.application.usecase.email.VerifyEmailUseCase
import br.com.fitogether.api.domain.dto.request.authentication.LoginRequest
import br.com.fitogether.api.domain.dto.request.user.CreateUserRequest
import br.com.fitogether.api.domain.dto.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.dto.response.ValidateEmailResponse
import br.com.fitogether.api.domain.dto.request.user.ValidateEmailRequest
import br.com.fitogether.api.domain.dto.response.AuthenticationResponse
import br.com.fitogether.api.domain.dto.response.ValidateCodeResponse
import br.com.fitogether.api.domain.service.user.UserService
import br.com.fitogether.api.interfaceAdapters.controller.base.BaseController
import br.com.fitogether.api.interfaceAdapters.dto.request.email.VerifyEmailRequest
import br.com.fitogether.api.interfaceAdapters.dto.response.email.VerifyEmailResponse

import jakarta.validation.Valid

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(
    authenticationService: AuthenticationService,
    private val userService: UserService,
    private val verifyEmailUseCase: VerifyEmailUseCase
) : BaseController(authenticationService = authenticationService) {

    @PostMapping(value = ["verify-email"])
    fun verifyEmail(
        @RequestBody @Valid request: VerifyEmailRequest
    ) : VerifyEmailResponse {
        return execute {
            verifyEmailUseCase(email = request.email)
        }
    }

    @PostMapping(value = ["validate-code"])
    fun validateCode(@RequestBody @Valid request: ValidateCodeRequest) : ValidateCodeResponse {
        return userService.validateCode(request = request)
    }

    @PostMapping
    fun create(@RequestBody @Valid request: CreateUserRequest) : AuthenticationResponse {
        return userService.createUser(request = request)
    }

    @PostMapping(value = ["login"])
    fun login(@RequestBody login: LoginRequest) : AuthenticationResponse {
        return userService.authenticate(login = login)
    }
}