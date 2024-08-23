package br.com.fitogether.api.controller.user

import br.com.fitogether.api.domain.model.request.user.CreateUserRequest
import br.com.fitogether.api.domain.model.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.model.response.ValidateEmailResponse
import br.com.fitogether.api.domain.model.request.user.ValidateEmailRequest
import br.com.fitogether.api.domain.service.user.UserService

import jakarta.validation.Valid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController(
    @Autowired private val userService: UserService
) {

    @PostMapping(value = ["validateEmail"])
    fun validateEmail(
        @RequestBody @Valid request: ValidateEmailRequest
    ) : ValidateEmailResponse {
        return userService.validateEmail(request = request)
    }

    @PostMapping(value = ["validateCode"])
    fun validateCode(@RequestBody @Valid request: ValidateCodeRequest) {
        userService.validateCode(request = request)
    }

    @PostMapping
    fun create(@RequestBody request: CreateUserRequest) {
        userService.createUser(request = request)
    }
}