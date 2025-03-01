package br.com.fitogether.api.domain.service.user

import br.com.fitogether.api.config.security.SecurityConfig
import br.com.fitogether.api.core.enums.GeneralError
import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.core.exception.custom.RuleException
import br.com.fitogether.api.core.exception.custom.ValidateCodeException
import br.com.fitogether.api.data.entity.exercise.UserExerciseEntity
import br.com.fitogether.api.data.entity.goal.UserGoalEntity
import br.com.fitogether.api.data.entity.password_reset_token.PasswordResetTokenEntity
import br.com.fitogether.api.data.entity.preference.PreferenceEntity
import br.com.fitogether.api.data.entity.preference.PreferenceGenderEntity
import br.com.fitogether.api.data.entity.preference.PreferenceGymEntity
import br.com.fitogether.api.data.entity.preference.PreferenceScheduleEntity
import br.com.fitogether.api.data.entity.user.UserEntity
import br.com.fitogether.api.data.mapper.user.*
import br.com.fitogether.api.data.mapper.validationCode.toValidateEmailResponse
import br.com.fitogether.api.data.repository.code.ValidationCodeRepository
import br.com.fitogether.api.data.repository.exercise.ExerciseRepository
import br.com.fitogether.api.data.repository.exercise.UserExerciseRepository
import br.com.fitogether.api.data.repository.experience.ExperienceRepository
import br.com.fitogether.api.data.repository.gender.GenderRepository
import br.com.fitogether.api.data.repository.goal.GoalRepository
import br.com.fitogether.api.data.repository.goal.UserGoalRepository
import br.com.fitogether.api.data.repository.gym.GymRepository
import br.com.fitogether.api.data.repository.password_reset_token.PasswordResetTokenRepository
import br.com.fitogether.api.data.repository.preference.PreferenceGenderRepository
import br.com.fitogether.api.data.repository.preference.PreferenceGymRepository
import br.com.fitogether.api.data.repository.preference.PreferenceRepository
import br.com.fitogether.api.data.repository.preference.PreferenceScheduleRepository
import br.com.fitogether.api.data.repository.user.UserRepository
import br.com.fitogether.api.domain.dto.request.authentication.LoginRequest
import br.com.fitogether.api.domain.dto.request.registration.PreferencesRequest
import br.com.fitogether.api.domain.dto.request.user.CreateUserRequest
import br.com.fitogether.api.domain.dto.request.user.ValidateCodeRequest
import br.com.fitogether.api.domain.dto.request.user.ValidateEmailRequest
import br.com.fitogether.api.domain.dto.response.AuthenticationResponse
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.ValidateCodeResponse
import br.com.fitogether.api.domain.dto.response.ValidateEmailResponse
import br.com.fitogether.api.domain.model.exercise.Exercise
import br.com.fitogether.api.domain.model.goal.Goal
import br.com.fitogether.api.domain.service.aws.S3Service
import br.com.fitogether.api.domain.service.code.ValidationCodeService
import br.com.fitogether.api.domain.service.email.EmailService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.*
import javax.security.auth.login.LoginException
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val genderRepository: GenderRepository,
    private val goalRepository: GoalRepository,
    private val exerciseRepository: ExerciseRepository,
    private val experienceRepository: ExperienceRepository,
    private val preferenceRepository: PreferenceRepository,
    private val preferenceScheduleRepository: PreferenceScheduleRepository,
    private val validationCodeRepository: ValidationCodeRepository,
    private val passwordResetTokenRepository: PasswordResetTokenRepository,
    private val gymRepository: GymRepository,
    private val userExerciseRepository: UserExerciseRepository,
    private val userGoalRepository: UserGoalRepository,
    private val preferenceGenderRepository: PreferenceGenderRepository,
    private val preferenceGymRepository: PreferenceGymRepository,
    private val validationCodeService: ValidationCodeService,
    private val emailService: EmailService,
    private val s3Service: S3Service,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val securityConfig: SecurityConfig
) {
    fun validateEmail(request: ValidateEmailRequest): ValidateEmailResponse {
        try {
            val validationCode = validationCodeRepository.findByEmail(request.email)

            validationCode?.let {
                if (!validationCode.validated) {
                    validationCodeService.setValidationCode(email = request.email)
                }
            } ?: run {
                validationCodeService.setValidationCode(email = request.email)
            }

            return validationCode.toValidateEmailResponse()
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun validateCode(request: ValidateCodeRequest): ValidateCodeResponse {
        try {
            if (validationCodeService.validateCode(request = request)) {
                val user = userRepository.findByEmail(request.email).getOrNull()
                return ValidateCodeResponse(
                    userId = user?.id,
                    registrationStep = user?.registrationStep ?: RegistrationStep.START
                )
            } else {
                throw ValidateCodeException(
                    message = GeneralError.EV003.message, internalCode = GeneralError.EV003.code
                )
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun createUser(request: CreateUserRequest): AuthenticationResponse {
        try {
            val user = userRepository.save(
                request.copy(
                    password = bCryptPasswordEncoder.encode(request.password),
                ).toEntity()
            )

            return setUserAccessToken(userEntity = user)
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun setUserAccessToken(userEntity: UserEntity): AuthenticationResponse {
        try {
            return userRepository.save(
                userEntity.copy(
                    accessToken = securityConfig.generateToken(user = userEntity)
                )
            ).toAuthenticationResponse()
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun isEmailAvailable(email: String): UserEntity? {
        try {
            return userRepository.findByEmail(email = email).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun authenticate(login: LoginRequest): AuthenticationResponse {
        try {
            val user = userRepository.findByEmail(email = login.email).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
            }

            if (bCryptPasswordEncoder.matches(login.password, user.password)) {
                return setUserAccessToken(userEntity = user)
            } else {
                throw LoginException(GeneralError.EAUTH001.message)
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun isUsernameAvailable(username: String): UserEntity? {
        try {
            return userRepository.findByUsername(username = username).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun updateGender(genderId: Long, userId: Long): UserResponse {
        try {
            val user = userRepository.findById(userId).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
            }
            val gender = genderRepository.findById(genderId).orElseThrow()
            return userRepository.save(
                user.copy(gender = gender, registrationStep = RegistrationStep.GOALS)
            ).toModel().toUserResponse()
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun setUserGoals(userId: Long, goals: List<Goal>): UserResponse {
        try {
            val user = userRepository.findById(userId).orElseThrow()
            val findGoals = goalRepository.findAllById(goals.map { it.id }).toMutableSet()

            // deleta os objetivos existentes
            userGoalRepository.findByUserId(userId).forEach { userGoal ->
                userGoal.softDelete()
            }

            // salva os novos objetivos
            val userGoals = findGoals.map { goal ->
                UserGoalEntity(
                    user = user,
                    goal = goal
                )
            }

            return userRepository.save(
                user.copy(registrationStep = RegistrationStep.EXERCISES, userGoals = userGoals.toMutableList())
            ).toModel().toUserResponse()
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun setUserExercises(userId: Long, exercises: List<Exercise>): UserResponse {
        try {
            val user = userRepository.findById(userId).orElseThrow()
            val findExercises = exerciseRepository.findAllById(exercises.map { it.id }).toMutableSet()

            // deleta os exercicios existentes
            userExerciseRepository.findByUserId(userId).forEach { userExercise ->
                userExercise.softDelete()
            }

            // salva os novos exercicios
            val userExercises = findExercises.map { exercise ->
                UserExerciseEntity(
                    user = user,
                    exercise = exercise
                )
            }

            return userRepository.save(
                user.copy(registrationStep = RegistrationStep.EXPERIENCE, userExercises = userExercises.toMutableList())
            ).toModel().toUserResponse()
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun setUserExperience(userId: Long, experienceId: Long): UserResponse {
        try {
            val user = userRepository.findById(userId).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
            }
            val experienceEntity = experienceRepository.findById(experienceId).orElseThrow()

            return userRepository.save(
                user.copy(experience = experienceEntity, registrationStep = RegistrationStep.PREFERENCES)
            ).toModel().toUserResponse()
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun setUserPreferences(userId: Long, preferences: PreferencesRequest): UserResponse {
        try {
            val user = userRepository.findById(userId).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
            }
            if (user.preferences != null) {
                throw RuleException(
                    HttpStatus.BAD_REQUEST,
                    "As preferências do usuário já foram definidas"
                )
            }
            val genders = genderRepository.findAllById(preferences.genders.map { it.id }).toMutableSet()
            val gyms = gymRepository.findAllById(preferences.gyms.map { it.id }).toMutableSet()

            val savedPreferences = preferenceRepository.save(
                PreferenceEntity(
                    user = user,
                    startAge = preferences.startAge,
                    endAge = preferences.endAge,
                    radiusDistance = preferences.radiusDistance,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now()
                )
            )

            val preferenceGenders = genders.map { gender ->
                PreferenceGenderEntity(
                    preference = savedPreferences,
                    gender = gender
                )
            }
            savedPreferences.preferenceGenders.addAll(preferenceGenders)

            val preferenceGyms = gyms.map { gym ->
                PreferenceGymEntity(
                    preference = savedPreferences,
                    gym = gym
                )
            }
            savedPreferences.preferenceGyms.addAll(preferenceGyms)

            val schedules = preferences.schedule.map { scheduleData ->
                PreferenceScheduleEntity(
                    preference = savedPreferences,
                    dayWeek = scheduleData.day,
                    startTime = scheduleData.startTime,
                    endTime = scheduleData.endTime
                )
            }
            savedPreferences.schedules.addAll(schedules)

            return userRepository.save(
                user.copy(preferences = savedPreferences, registrationStep = RegistrationStep.FINISHED)
            ).toModel().toUserResponse()

        } catch (exception: Exception) {
            throw exception
        }
    }

    fun updateUserPreferences(userId: Long, preferences: PreferencesRequest): UserResponse {
        try {
            val user = userRepository.findById(userId).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
            }
            val existingPreferences = user.preferences
                ?: throw RuleException(
                    HttpStatus.BAD_REQUEST,
                    "As preferências do usuário não foram definidas anteriormente."
                )

            existingPreferences.startAge = preferences.startAge
            existingPreferences.endAge = preferences.endAge
            existingPreferences.radiusDistance = preferences.radiusDistance
            existingPreferences.updatedAt = LocalDateTime.now()

            existingPreferences.preferenceGenders.clear()

            val genders = genderRepository.findAllById(preferences.genders.map { it.id }).toMutableSet()
            val preferenceGenders = genders.map { gender ->
                PreferenceGenderEntity(
                    preference = existingPreferences,
                    gender = gender
                )
            }
            existingPreferences.preferenceGenders.addAll(preferenceGenders)

            existingPreferences.preferenceGyms.clear()
            val gyms = gymRepository.findAllById(preferences.gyms.map { it.id }).toMutableSet()
            val preferenceGyms = gyms.map { gym ->
                PreferenceGymEntity(
                    preference = existingPreferences,
                    gym = gym
                )
            }
            existingPreferences.preferenceGyms.addAll(preferenceGyms)

            existingPreferences.schedules.clear()
            val schedules = preferences.schedule.map { scheduleData ->
                PreferenceScheduleEntity(
                    preference = existingPreferences,
                    dayWeek = scheduleData.day,
                    startTime = scheduleData.startTime,
                    endTime = scheduleData.endTime
                )
            }
            existingPreferences.schedules.addAll(schedules)

            preferenceRepository.save(existingPreferences)
            return userRepository.save(user.copy(preferences = existingPreferences)).toModel().toUserResponse()
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun uploadPhoto(userId: Long, file: MultipartFile): UserResponse {
        try {
            val user = userRepository.findById(userId).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
            }

            val photoUrl = s3Service.uploadUserPhoto(userId, file)

            return userRepository.save(
                user.copy(photo = photoUrl)
            ).toModel().toUserResponse()

        } catch (exception: Exception) {
            throw exception
        }
    }

    fun forgotPassword(email: String) {
        try {
            val user = userRepository.findByEmail(email).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
            }

            // Gera um token único e define validade de 1 hora
            val token = UUID.randomUUID().toString()
            val expirationTime = LocalDateTime.now().plusHours(1)

            // Remove tokens antigos do usuário, se existirem
            passwordResetTokenRepository.deleteByUser(user)

            // Cria um novo token
            val passwordResetToken = PasswordResetTokenEntity(
                user = user,
                token = token,
                expiresAt = expirationTime
            )
            passwordResetTokenRepository.save(passwordResetToken)

            // Envia e-mail com o link de redefinição
            emailService.sendPasswordResetEmail(user.email, token)
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun passwordReset(token: String, password: String) {
        try {
            val resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow { RuleException(HttpStatus.NOT_FOUND, "Token inválido ou expirado") }

            // Verifica se o token ainda é válido
            if (resetToken.expiresAt.isBefore(LocalDateTime.now())) {
                throw RuleException(HttpStatus.BAD_REQUEST, "Token expirado")
            }

            // Atualiza a senha do usuário
            userRepository.save(
                resetToken.user.copy(password = bCryptPasswordEncoder.encode(password))
            )
            // Remove o token após a redefinição
            passwordResetTokenRepository.delete(resetToken)
        } catch (exception: Exception) {
            throw exception
        }
    }
}