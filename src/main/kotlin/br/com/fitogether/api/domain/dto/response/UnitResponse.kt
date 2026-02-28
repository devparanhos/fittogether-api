package br.com.fitogether.api.domain.dto.response

import br.com.fitogether.api.core.enums.UnitType
import br.com.fitogether.api.domain.model.address.Address
import br.com.fitogether.api.domain.model.exercise.Exercise
import com.fasterxml.jackson.annotation.JsonProperty

data class UnitResponse(
    @field:JsonProperty("id")
    val id: Long?,

    @field:JsonProperty("companyName")
    val companyName: String?,

    @field:JsonProperty("name")
    val name: String,

    @field:JsonProperty("address")
    val address: Address?,

    @field:JsonProperty("type")
    val type: UnitType,

    @field:JsonProperty("exercises")
    val exercises: List<Exercise>,
)
