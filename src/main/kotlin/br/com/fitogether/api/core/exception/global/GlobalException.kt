package br.com.fitogether.api.core.exception.global

import br.com.fitogether.api.core.error.field.FieldError

class GlobalException(
    val message: String?,
    val internalCode: String,
    val statusCode: Int,
    val errors: List<FieldError>? = null
)