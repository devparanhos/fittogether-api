package br.com.fitogether.api.domain.dto.request.gym

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class GetGymsRequest(
    @field:JsonProperty("lat")
    @field:JsonAlias("lat")
    @field:NotNull(message = "Você precisa informar a latitude")
    val lat: String,

    @field:JsonProperty("lng")
    @field:JsonAlias("lng")
    @field:NotNull(message = "Você precisa informar a longitude")
    val lng: String,

    @field:JsonProperty("radius_distance")
    @field:JsonAlias("radius_distance")
    @field:NotNull(message = "Você precisa informar a distância máxima desejada")
    val radiusDistance: Int,
)
