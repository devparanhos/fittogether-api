package br.com.fitogether.api.core.error.field

data class FieldError(
    val field: String,
    val message: String?
)
