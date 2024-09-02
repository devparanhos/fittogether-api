package br.com.fitogether.api.domain.service.user

import br.com.fitogether.api.config.security.SecurityConfig
import br.com.fitogether.api.core.enums.GeneralError
import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.core.exception.custom.ValidateCodeException
import br.com.fitogether.api.data.mapper.user.toEntity
import br.com.fitogether.api.data.mapper.user.toValidateEmailResponse
import br.com.fitogether.api.data.repository.user.UserRepository
import br.com.fitogether.api.domain.model.request.user.CreateUserRequest
import br.com.fitogether.api.domain.model.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.model.request.user.ValidateEmailRequest
import br.com.fitogether.api.domain.model.response.UserResponse
import br.com.fitogether.api.domain.model.response.ValidateCodeResponse
import br.com.fitogether.api.domain.model.response.ValidateEmailResponse
import br.com.fitogether.api.domain.service.code.ValidationCodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val validationCodeService: ValidationCodeService,
    @Autowired private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val securityConfig: SecurityConfig
) : UserDetailsService {
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

    fun validateCode(request: ValidateCodeRequest) : ValidateCodeResponse {
        if (validationCodeService.validateCode(request = request)) {
            val user = userRepository.findByEmail(request.email)
            return ValidateCodeResponse(
                userId = user?.id,
                registrationStep = user?.registrationStep ?: RegistrationStep.START
            )
        } else {
            throw ValidateCodeException(
                message = GeneralError.EV003.message,
                internalCode = GeneralError.EV003.code
            )
        }
    }

    fun createUser(request: CreateUserRequest) : UserResponse {
        val user = userRepository.save(
            request.copy(
                password = bCryptPasswordEncoder.encode(request.password)
            ).toEntity()
        )

        return UserResponse(accessToken = securityConfig.generateToken())
    }

    fun isEmailAvailable(email: String): Boolean {
        return userRepository.findByEmail(email) == null
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByUsername(username = username) ?: throw Exception()
    }
}