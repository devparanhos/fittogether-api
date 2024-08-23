package br.com.fitogether.api.data.mapper.user

import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.data.entity.user.UserEntity
import br.com.fitogether.api.domain.model.request.user.CreateUserRequest
import br.com.fitogether.api.domain.model.response.ValidateEmailResponse

fun UserEntity?.toValidateEmailResponse() = ValidateEmailResponse(
    status = this?.registrationStatus ?: UserRegistrationStatus.NOT_FOUND,
    message = this?.registrationStatus?.message,
    sendingCode = this?.registrationStatus != UserRegistrationStatus.CONCLUDED
)

fun CreateUserRequest.toEntity() = UserEntity(
    email = this.email,
    password = this.password,
    cellphone = this.cellphone,
    name = this.name,
    birthDate = this.birthDate
)