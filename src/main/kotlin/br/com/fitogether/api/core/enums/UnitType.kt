package br.com.fitogether.api.core.enums

/**
 * Tipo do estabelecimento (unit): Academia, clube, etc.
 */
enum class UnitType(val value: String) {
    GYM(value = "GYM"),
    CLUB(value = "CLUB"),
    STUDIO(value = "STUDIO"),
    OTHER(value = "OTHER"),
}
