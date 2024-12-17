package br.com.fitogether.api.domain.dto.request.home

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class HomeScreenRequest(
    @field:NotNull(message = "Latitude is required")
    @field:Min(value = -90, message = "Latitude must be >= -90")
    @field:Max(value = 90, message = "Latitude must be <= 90")
    val lat: Double,

    @field:NotNull(message = "Longitude is required")
    @field:Min(value = -180, message = "Longitude must be >= -180")
    @field:Max(value = 180, message = "Longitude must be <= 180")
    val lng: Double,

    @field:Min(value = 5000, message = "Longitude must be >= 5000 meters")
    @field:Max(value = 30000, message = "Longitude must be <= 30000 meters")
    val radius: Double = 15.00,

    val exercise: List<String>? = null
)
