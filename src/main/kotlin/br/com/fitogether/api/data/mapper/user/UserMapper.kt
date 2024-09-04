package br.com.fitogether.api.data.mapper.user

import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.core.extension.formatDate
import br.com.fitogether.api.data.entity.user.UserEntity
import br.com.fitogether.api.domain.dto.request.user.CreateUserRequest
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.ValidateEmailResponse

fun UserEntity?.toValidateEmailResponse() = ValidateEmailResponse(
    status = this?.registrationStatus ?: UserRegistrationStatus.NOT_FOUND,
    message = this?.registrationStatus?.message,
    sendingCode = this?.registrationStatus != UserRegistrationStatus.CONCLUDED
)

fun UserEntity.toUserResponse() = UserResponse(
    accessToken = this.accessToken,
    userId = this.id,
    email = this.email,
    name = this.name,
    username = this.username,
    registrationStep = this.registrationStep
)

fun CreateUserRequest.toEntity() = UserEntity(
    email = this.email,
    password = this.password,
    cellphone = this.cellphone,
    name = this.name,
    birthDate = this.birthDate.formatDate(),
    username = this.username
)