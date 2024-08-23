package br.com.fitogether.api.core.enums

enum class GeneralError(val message: String, val code: String) {

    EV001(message = "Existem campos com dados inválidos", code = "EV-001"),
    EV002(message = "O e-mail informado não contém um código atribuído", code = "EV-002"),
    EV003(message = "O código informado é inválido", code = "EV-003"),
    EV004(message = "O código informado expirou", code = "EV-004"),

    ER001(message = "Método HTTP informando não é suportado para esse endpoint", code = "ER-001"),
    ESM001(message = "Não foi possível enviar o e-mail", code = "ESM-001"),
}