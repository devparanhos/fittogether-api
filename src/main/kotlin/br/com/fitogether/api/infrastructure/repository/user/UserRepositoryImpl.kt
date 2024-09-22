package br.com.fitogether.api.infrastructure.repository.user

import br.com.fitogether.api.domain.model.user.User
import br.com.fitogether.api.domain.repository.user.UserRepository
import br.com.fitogether.api.infrastructure.database.mapper.user.toModel
import br.com.fitogether.api.infrastructure.database.repository.user.UserJpaRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {
    override fun findUserByUsername(username: String): User? {
        return userJpaRepository.findByUsername(username = username)?.toModel()
    }

    override fun verifyEmail(email: String): Boolean {
        TODO("Not yet implemented")
    }
}