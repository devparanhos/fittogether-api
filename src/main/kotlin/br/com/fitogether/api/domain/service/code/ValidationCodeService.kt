package br.com.fitogether.api.domain.service.code

import br.com.fitogether.api.core.enums.GeneralError
import br.com.fitogether.api.core.exception.custom.ValidateCodeException
import br.com.fitogether.api.infrastructure.database.entity.code.ValidationCodeEntity
import br.com.fitogether.api.infrastructure.database.repository.code.ValidationCodeJpaRepository
import br.com.fitogether.api.domain.dto.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.service.email.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class ValidationCodeService(
    @Autowired private val validationCodeRepository: ValidationCodeJpaRepository,
    @Autowired private val emailService: EmailService
) {


    fun validateCode(request: ValidateCodeRequest) : Boolean {
        val entity = validationCodeRepository.findByEmail(request.email)

        when {
            entity == null -> {
                throw ValidateCodeException(
                    message = GeneralError.EV002.message,
                    internalCode = GeneralError.EV002.code
                )
            }

            entity.code != request.code.toInt() -> {
                throw ValidateCodeException(
                    message = GeneralError.EV003.message,
                    internalCode = GeneralError.EV003.code
                )
            }

            isCodeExpired(codeTimeCreation = entity.createdAt, secondsThreshold = entity.duration) -> {
                throw ValidateCodeException(
                    message = GeneralError.EV004.message,
                    internalCode = GeneralError.EV004.code
                )
            }

            else -> {
                validationCodeRepository.save(entity.copy(validated = true))
                return true
            }
        }
    }

    fun isEmailValidated(email: String) : Boolean {
        return validationCodeRepository.findByEmail(email = email)?.validated ?: false
    }


    private fun isCodeExpired(codeTimeCreation: LocalDateTime, secondsThreshold: Int): Boolean {
        val now = LocalDateTime.now()
        val duration: Duration = Duration.between(codeTimeCreation, now)
        return duration.seconds > secondsThreshold
    }
}