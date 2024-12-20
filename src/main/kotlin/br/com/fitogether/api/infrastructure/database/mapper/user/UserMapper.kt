package br.com.fitogether.api.infrastructure.database.mapper.user

import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.core.extension.formatDate
import br.com.fitogether.api.infrastructure.database.entity.user.UserEntity
import br.com.fitogether.api.infrastructure.database.mapper.exercise.toModel
import br.com.fitogether.api.infrastructure.database.mapper.experience.toModel
import br.com.fitogether.api.infrastructure.database.mapper.gender.toModel
import br.com.fitogether.api.infrastructure.database.mapper.goal.toModel
import br.com.fitogether.api.infrastructure.database.mapper.gym.toModel
import br.com.fitogether.api.infrastructure.database.mapper.preference.toModel
import br.com.fitogether.api.domain.dto.request.user.CreateUserRequest
import br.com.fitogether.api.domain.dto.response.AuthenticationResponse
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.ValidateEmailResponse
import br.com.fitogether.api.domain.model.user.User

fun UserEntity?.toValidateEmailResponse() = ValidateEmailResponse(
    status = this?.registrationStatus ?: UserRegistrationStatus.NOT_FOUND,
    message = this?.registrationStatus?.message,
    sendingCode = this?.registrationStatus != UserRegistrationStatus.CONCLUDED
)

fun UserEntity.toAuthenticationResponse() = AuthenticationResponse(
    accessToken = this.accessToken,
    user = this.toModel().toUserResponse()
)

fun UserEntity.toModel() = User(
    id = this.id,
    name = this.name,
    gender = this.gender?.toModel()?.name,
    username = this.username,
    email = this.email,
    registrationStep = this.registrationStep,
    registrationStatus = this.registrationStatus,
    goals = this.goals.map { it.toModel() },
    exercises = this.exercises.map { it.toModel() },
    experience = this.experience?.toModel()?.name,
    gyms = this.gyms.map { it.toModel() },
    preferences = this.preferences?.toModel()
)

fun User.toUserResponse() = UserResponse(
    id = this.id,
    email = this.email,
    name = this.name,
    username = this.username,
    registrationStep = this.registrationStep,
    registrationStatus = this.registrationStatus,
    gender = this.gender,
    goals = this.goals,
    exercises = this.exercises,
    experience = this.experience,
    gyms = this.gyms,
    preferences = this.preferences
)

fun CreateUserRequest.toEntity() = UserEntity(
    email = this.email,
    password = this.password,
    cellphone = this.cellphone,
    name = this.name,
    birthDate = this.birthDate.formatDate(),
    username = this.username
)