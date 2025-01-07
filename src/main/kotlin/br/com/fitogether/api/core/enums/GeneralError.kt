package br.com.fitogether.api.core.enums

enum class GeneralError(val message: String, val code: String) {

    //Erros de validação
    EV001(message = "Existem campos com dados inválidos", code = "EV-001"),
    EV002(message = "O e-mail informado não contém um código atribuído", code = "EV-002"),
    EV003(message = "O código informado é inválido", code = "EV-003"),
    EV004(message = "O código informado expirou", code = "EV-004"),
    EV005(message = "O corpo da requisição é inválido ou está ausente.", code = "EV-005"),

    //Erros de request
    ER001(message = "Método HTTP informado não é suportado para esse endpoint", code = "ER-001"),

    //Erros de envio de email
    ESM001(message = "Não foi possível enviar o e-mail", code = "ESM-001"),

    //Erros de autenticação
    EAUTH001(message = "Não foi possível fazer login. Credenciais inválidas", code = "EAUTH-001"),
    EAUTH002(message = "Acesso negado, a autenticação falhou", code = "EAUTH-002"),
    EAUTH003(message = "Acesso negado. Você não tem permissão para acessar este recurso.", code = "EAUTH-003"),

    //Erros internos de servidor
    ISV001(message = "Erro ao acessar dados relacionados. Por favor, tente novamente mais tarde.", code = "ISV-001")
}