package br.com.fitogether.api.core.enums

/**
 * Etapa do fluxo "completar cadastro" (onboarding após a conta existir).
 * Indica em qual parte o usuário está: não finalizado → gênero → objetivos → exercícios → experiência → preferências → finalizado.
 */
enum class RegistrationStep(val value: String) {
    UNFINISHED(value = "UNFINISHED"),
    START(value = "START"),
    GENDER(value = "GENDER"),
    GOALS(value = "GOALS"),
    EXERCISES(value = "EXERCISES"),
    EXPERIENCE(value = "EXPERIENCE"),
    PREFERENCES(value = "PREFERENCES"),
    FINISHED(value = "FINISHED"),
}