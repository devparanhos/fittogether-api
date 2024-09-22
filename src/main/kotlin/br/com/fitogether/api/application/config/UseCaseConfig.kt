package br.com.fitogether.api.application.config

import br.com.fitogether.api.application.usecase.email.VerifyEmailUseCase
import br.com.fitogether.api.application.usecase.user.GetUserByUsernameUseCase
import br.com.fitogether.api.domain.repository.user.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig {

    @Bean
    fun getUserByUsernameUseCase(userRepository: UserRepository): GetUserByUsernameUseCase {
        return GetUserByUsernameUseCase(userRepository = userRepository)
    }

    @Bean
    fun verifyEmailUseCase(userRepository: UserRepository): VerifyEmailUseCase {
        return VerifyEmailUseCase(userRepository = userRepository)
    }
}