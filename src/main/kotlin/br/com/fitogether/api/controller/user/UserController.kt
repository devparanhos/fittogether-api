package br.com.fitogether.api.controller.user

import br.com.fitogether.api.config.security.SecurityConfig
import br.com.fitogether.api.domain.model.request.authentication.LoginRequest
import br.com.fitogether.api.domain.model.request.user.CreateUserRequest
import br.com.fitogether.api.domain.model.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.model.response.ValidateEmailResponse
import br.com.fitogether.api.domain.model.request.user.ValidateEmailRequest
import br.com.fitogether.api.domain.model.response.UserResponse
import br.com.fitogether.api.domain.model.response.ValidateCodeResponse
import br.com.fitogether.api.domain.service.user.UserService

import jakarta.validation.Valid
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(
    private val userService: UserService,
    private val encoder: BCryptPasswordEncoder
) {

    @PostMapping(value = ["validate-email"])
    fun validateEmail(
        @RequestBody @Valid request: ValidateEmailRequest
    ) : ValidateEmailResponse {
        return userService.validateEmail(request = request)
    }

    @PostMapping(value = ["validate-code"])
    fun validateCode(@RequestBody @Valid request: ValidateCodeRequest) : ValidateCodeResponse {
        return userService.validateCode(request = request)
    }

    @PostMapping
    fun create(@RequestBody @Valid request: CreateUserRequest) : UserResponse {
        return userService.createUser(request = request)
    }

    @PostMapping(value = ["login"])
    fun login(@RequestBody login: LoginRequest) : UserResponse {
        return userService.authenticate(login = login)
    }

    @GetMapping
    fun teste() : String{
        return "teste"
    }
}