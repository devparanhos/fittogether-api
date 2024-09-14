package br.com.fitogether.api.core.enums

enum class Experiences(val value: String, val description: String? = null) {
    BEGINNER(value = "Iniciante"),
    REGULAR(value = "Intermediário"),
    ADVANCED(value = "Avançado"),
    EXPERIENCED(value = "Experiente"),
}