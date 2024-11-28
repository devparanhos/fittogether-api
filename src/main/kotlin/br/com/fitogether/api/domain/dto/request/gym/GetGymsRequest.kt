package br.com.fitogether.api.domain.dto.request.gym

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class GetGymsRequest(
    @field:NotNull(message = "Latitude is required")
    @field:Min(value = -90, message = "Latitude must be >= -90")
    @field:Max(value = 90, message = "Latitude must be <= 90")
    val lat: Double?,

    @field:NotNull(message = "Longitude is required")
    @field:Min(value = -180, message = "Longitude must be >= -180")
    @field:Max(value = 180, message = "Longitude must be <= 180")
    val lng: Double?
)
