package br.com.fitogether.api.domain.dto.response

import br.com.fitogether.api.domain.model.address.Address
import com.fasterxml.jackson.annotation.JsonProperty

data class GymResponse(
    @field:JsonProperty("id")
    val id: Long?,

    @field:JsonProperty("name")
    val name: String,

    @field:JsonProperty("address")
    val address: Address?,
)
