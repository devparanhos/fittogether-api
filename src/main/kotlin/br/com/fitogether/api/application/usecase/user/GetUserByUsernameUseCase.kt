package br.com.fitogether.api.application.usecase.user

import br.com.fitogether.api.domain.model.user.User
import br.com.fitogether.api.domain.repository.user.UserRepository

class GetUserByUsernameUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(username: String): User? {
        return userRepository.findUserByUsername(username = username)
    }
}