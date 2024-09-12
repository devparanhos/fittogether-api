package br.com.fitogether.api.controller.base

import br.com.fitogether.api.data.entity.user.UserEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException

open class BaseController {
    fun <T>execute(userId: Long? = null, useCase: () -> T) : T {
        val authentication = SecurityContextHolder.getContext().authentication

        try {
            val currentUser = authentication.principal as UserEntity
            if (currentUser.id == userId || userId == null) {
                return useCase()
            } else {
                throw UsernameNotFoundException("User not found")
            }
        } catch (e:Exception) {
            throw UsernameNotFoundException("User not found")
        }
    }
}