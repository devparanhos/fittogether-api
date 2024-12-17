package br.com.fitogether.api.domain.model.user

data class Profile(
    val id: Long?,
    val email: String,
    val name: String,
    val username: String,
    val photo: String?,
    val level: String?
)