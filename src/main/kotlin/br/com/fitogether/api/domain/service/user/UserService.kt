package br.com.fitogether.api.domain.service.user

import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.data.mapper.user.toEntity
import br.com.fitogether.api.data.mapper.user.toValidateEmailResponse
import br.com.fitogether.api.data.repository.user.UserRepository
import br.com.fitogether.api.domain.model.request.user.CreateUserRequest
import br.com.fitogether.api.domain.model.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.model.request.user.ValidateEmailRequest
import br.com.fitogether.api.domain.model.response.ValidateEmailResponse
import br.com.fitogether.api.domain.service.code.ValidationCodeService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val validationCodeService: ValidationCodeService
) {
    fun validateEmail(request: ValidateEmailRequest) : ValidateEmailResponse  {
        try {
            val user = userRepository.findByEmail(request.email)

            if (user?.registrationStatus != UserRegistrationStatus.CONCLUDED) {
                validationCodeService.setValidationCode(email = request.email)
            }

            return user.toValidateEmailResponse()
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun validateCode(request: ValidateCodeRequest) {
        validationCodeService.validateCode(request = request)
    }

    fun createUser(request: CreateUserRequest) {
        userRepository.save(request.toEntity())
    }
}