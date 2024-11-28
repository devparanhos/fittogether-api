package br.com.fitogether.api.application.service

import br.com.fitogether.api.application.usecase.user.GetUserByUsernameUseCase
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase
) {

    fun hasPermission(userId: Long?): Boolean {
        userId?.let { id ->
            val authentication = SecurityContextHolder.getContext().authentication
            val username = authentication.principal as String
            val user = getUserByUsernameUseCase(username)

            return user != null && user.id == id
        } ?: return true
    }
}