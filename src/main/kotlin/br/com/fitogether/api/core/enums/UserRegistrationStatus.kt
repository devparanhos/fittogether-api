package br.com.fitogether.api.core.enums

enum class UserRegistrationStatus(val status: String, val message: String? = null) {
    NOT_FOUND(status = "NOT_FOUND"),
    IN_REGISTRATION(status = "IN_REGISTRATION"),
    CONCLUDED(status = "CONCLUDED", message = "Já existe um usuário com o e-mail informado.")
}