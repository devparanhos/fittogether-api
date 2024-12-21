package br.com.fitogether.api.domain.dto.request.exercise

import jakarta.validation.constraints.NotNull

data class ExercisePoolRequest(
    @field:NotNull(message = "Questions is required")
    val questions: List<Number>
)
