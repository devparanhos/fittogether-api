package br.com.fitogether.api.data.mapper.user

import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.core.extension.formatDate
import br.com.fitogether.api.data.entity.user.UserEntity
import br.com.fitogether.api.data.mapper.exercise.toModel
import br.com.fitogether.api.data.mapper.experience.toModel
import br.com.fitogether.api.data.mapper.gender.toModel
import br.com.fitogether.api.data.mapper.goal.toModel
import br.com.fitogether.api.data.mapper.preference.toModel
import br.com.fitogether.api.domain.dto.request.user.CreateUserRequest
import br.com.fitogether.api.domain.dto.response.AuthenticationResponse
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.ValidateEmailResponse
import br.com.fitogether.api.domain.model.user.Profile
import br.com.fitogether.api.domain.model.user.User
import kotlin.concurrent.thread

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
    photo = this.photo,
    goals = this.userGoals.map { it.goal.toModel() },
    exercises = this.userExercises.map { it.exercise.toModel() },
    experience = this.experience?.toModel()?.name,
    preferences = this.preferences?.toModel(),
    registrationStatus = this.registrationStatus
)

fun UserEntity.toProfileModel() = Profile(
    id = this.id,
    name = this.name,
    username = this.username,
    email = this.email,
    photo = this.photo,
    level = this.levelDescription
)

fun User.toUserResponse() = UserResponse(
    id = this.id,
    email = this.email,
    name = this.name,
    username = this.username,
    registrationStep = this.registrationStep,
    photo = this.photo,
    gender = this.gender,
    goals = this.goals,
    exercises = this.exercises,
    experience = this.experience,
    preference = this.preferences,
    registrationStatus = this.registrationStatus
)

fun CreateUserRequest.toEntity() = UserEntity(
    email = this.email,
    password = this.password,
    name = this.name,
    birthDate = this.birthDate.formatDate(),
    registrationStatus = UserRegistrationStatus.CREATED,
    registrationStep = RegistrationStep.FINISHED
)