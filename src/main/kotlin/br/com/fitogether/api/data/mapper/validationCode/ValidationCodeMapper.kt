package br.com.fitogether.api.data.mapper.validationCode

import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.data.entity.code.ValidationCodeEntity
import br.com.fitogether.api.domain.dto.response.ValidateEmailResponse

fun ValidationCodeEntity?.toValidateEmailResponse(): ValidateEmailResponse {
    var status = UserRegistrationStatus.NOT_FOUND
    if (this != null && !this.validated) {
        status = UserRegistrationStatus.IN_VALIDATION
    }
    if (this != null && this.validated) {
        status = UserRegistrationStatus.IN_REGISTRATION
    }
    if (this != null && this.validated && this.user != null) {
        status = UserRegistrationStatus.CREATED
    }
    return ValidateEmailResponse(
        status = status,
        message = status.message,
        sendingCode = status == UserRegistrationStatus.NOT_FOUND || status == UserRegistrationStatus.IN_VALIDATION
    )
}