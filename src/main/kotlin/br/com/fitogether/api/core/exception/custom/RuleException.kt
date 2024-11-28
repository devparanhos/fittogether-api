package br.com.fitogether.api.core.exception.custom

import org.springframework.http.HttpStatus

class RuleException(
    val status: HttpStatus,
    message: String
) : RuntimeException(message)