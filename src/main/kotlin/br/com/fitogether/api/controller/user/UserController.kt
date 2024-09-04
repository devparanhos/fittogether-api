package br.com.fitogether.api.controller.user

import br.com.fitogether.api.domain.dto.request.authentication.LoginRequest
import br.com.fitogether.api.domain.dto.request.user.CreateUserRequest
import br.com.fitogether.api.domain.dto.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.dto.response.ValidateEmailResponse
import br.com.fitogether.api.domain.dto.request.user.ValidateEmailRequest
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.ValidateCodeResponse
import br.com.fitogether.api.domain.service.user.UserService

import jakarta.validation.Valid

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(
    private val userService: UserService,
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