package br.com.fitogether.api.infrastructure.repository.user

import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.domain.model.user.User
import br.com.fitogether.api.domain.repository.user.UserRepository
import br.com.fitogether.api.domain.service.email.EmailService
import br.com.fitogether.api.infrastructure.database.entity.code.ValidationCodeEntity
import br.com.fitogether.api.infrastructure.database.mapper.user.toModel
import br.com.fitogether.api.infrastructure.database.repository.code.ValidationCodeJpaRepository
import br.com.fitogether.api.infrastructure.database.repository.user.UserJpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import kotlin.random.Random

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
    private val validateCodeJpaRepository: ValidationCodeJpaRepository,
    private val emailService: EmailService
) : UserRepository {
    override fun findUserByUsername(username: String): User? {
        return userJpaRepository.findByUsername(username = username)?.toModel()
    }

    override fun verifyEmail(email: String): User? {
        try {
            val user = userJpaRepository.findByEmail(email)

            user?.let {
                if (it.registrationStatus != UserRegistrationStatus.CONCLUDED) {
                    val validationCodeEntity = validateCodeJpaRepository.findByEmail(email)

                    val validationCode = validateCodeJpaRepository.save(
                        ValidationCodeEntity(
                            id = validationCodeEntity?.id,
                            email = email,
                            code = 100000 + Random.nextInt(900000),
                            createdAt = LocalDateTime.now()
                        )
                    )

                    emailService.sendValidationCode(to = email, code = validationCode.code)
                }

                return it.toModel()
            } ?: run {
                throw User
            }
        } catch (exception: Exception) {
            throw exception
        }
    }
}