package br.com.fitogether.api.domain.service.user

import br.com.fitogether.api.config.security.SecurityConfig
import br.com.fitogether.api.core.enums.GeneralError
import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.core.exception.custom.ValidateCodeException
import br.com.fitogether.api.data.entity.user.UserEntity
import br.com.fitogether.api.data.mapper.user.*
import br.com.fitogether.api.data.repository.gender.GenderRepository
import br.com.fitogether.api.data.repository.user.UserRepository
import br.com.fitogether.api.domain.dto.request.authentication.LoginRequest
import br.com.fitogether.api.domain.dto.request.registration.GenderRequest
import br.com.fitogether.api.domain.dto.request.user.CreateUserRequest
import br.com.fitogether.api.domain.dto.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.dto.request.user.ValidateEmailRequest
import br.com.fitogether.api.domain.dto.response.AuthenticationResponse
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.ValidateCodeResponse
import br.com.fitogether.api.domain.dto.response.ValidateEmailResponse
import br.com.fitogether.api.domain.service.code.ValidationCodeService

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.security.auth.login.LoginException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val genderRepository: GenderRepository,
    private val validationCodeService: ValidationCodeService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val securityConfig: SecurityConfig
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

    fun createUser(request: CreateUserRequest) : AuthenticationResponse {
        val user = userRepository.save(
            request.copy(
                password = bCryptPasswordEncoder.encode(request.password),
            ).toEntity()
        )

        return setUserAccessToken(userEntity = user)
    }

    fun setUserAccessToken(userEntity: UserEntity) : AuthenticationResponse {
        return userRepository.save(
            userEntity.copy(
                accessToken = securityConfig.generateToken(user = userEntity)
            )
        ).toAuthenticationResponse()
    }

    fun isEmailAvailable(email: String): Boolean {
        return userRepository.findByEmail(email) == null
    }

    fun authenticate(login: LoginRequest) : AuthenticationResponse {
        userRepository.findByEmail(email = login.email)?.let {
            if (bCryptPasswordEncoder.matches(login.password, it.password)) {
                return setUserAccessToken(userEntity = it)
            } else {
                throw LoginException(GeneralError.EAUTH001.message)
            }
        } ?: throw LoginException(GeneralError.EAUTH001.message)
    }

    fun isUsernameAvailable(username: String) : Boolean {
        return userRepository.findByUsername(username = username) == null
    }

    fun updateGender(genderId: Long, userId: Long) : UserResponse {
        val user = userRepository.findById(userId).orElseThrow()
        val gender = genderRepository.findById(genderId).orElseThrow()
        return userRepository.save(
            user.copy(gender = gender, registrationStep = RegistrationStep.GOALS)
        ).toModel().toUserResponse()
    }
}