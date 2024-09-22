package br.com.fitogether.api.domain.repository.user

import br.com.fitogether.api.domain.model.user.User

interface UserRepository {
    fun findUserByUsername(username: String): User?
    fun verifyEmail(email: String): Boolean
}