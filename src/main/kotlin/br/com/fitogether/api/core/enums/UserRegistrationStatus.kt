package br.com.fitogether.api.core.enums

/**
 * Situação da criação da conta do usuário (validação de e-mail → conta criada).
 * Usado para controlar o fluxo de "criar usuário" em si.
 */
enum class UserRegistrationStatus(val status: String, val message: String? = null) {
    NOT_FOUND(status = "NOT_FOUND", message = "Nenhum usuário encontrado. Valide o e-mail para prosseguir"),
    IN_VALIDATION(status = "IN_VALIDATION", message = "Usuário precisa concluir a validação do e-mail"),
    IN_REGISTRATION(status = "IN_REGISTRATION", message = "Usuário já validou o e-mail, porém precisa fazer o cadastro"),
    CONCLUDED(status = "CONCLUDED", message = "Já existe um usuário com o e-mail informado."),
    CREATED(status = "CREATED", message = "Já existe um usuário com o e-mail informado.")
}