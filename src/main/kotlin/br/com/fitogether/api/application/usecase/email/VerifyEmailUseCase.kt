package br.com.fitogether.api.application.usecase.email

import br.com.fitogether.api.domain.repository.user.UserRepository
import br.com.fitogether.api.interfaceAdapters.dto.response.email.VerifyEmailResponse

class VerifyEmailUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(email: String) : VerifyEmailResponse {
        return userRepository.verifyEmail(email = email)
    }
}