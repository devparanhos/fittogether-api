package br.com.fitogether.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SignupController {

    @RequestMapping("/signup/verifyEmail")
    fun verifyEmail() : Boolean {
        return true
    }
}