package br.com.fitogether.api.domain.model.gym

import br.com.fitogether.api.domain.model.address.Address

data class Gym(
    val id: Long?,
    val name: String,
    val address: Address?
)
