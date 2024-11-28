package br.com.fitogether.api.core.enums

enum class RegistrationStep(val value: String) {
    START(value = "START"),
    GENDER(value = "GENDER"),
    GOALS(value = "GOALS"),
    EXERCISES(value = "EXERCISES"),
    EXPERIENCE(value = "EXPERIENCE"),
    PREFERENCES(value = "PREFERENCES"),
    FINISHED(value = "FINISHED"),
}