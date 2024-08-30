package br.com.fitogether.api.core.exception.custom

import org.springframework.http.HttpStatus

data class ValidateCodeException(
    override val message: String,
    val internalCode: String,
    val statusCode: HttpStatus = HttpStatus.BAD_REQUEST
) : RuntimeException()