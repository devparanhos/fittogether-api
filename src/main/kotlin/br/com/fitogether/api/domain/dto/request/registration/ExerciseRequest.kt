package br.com.fitogether.api.domain.dto.request.registration

import br.com.fitogether.api.domain.model.exercise.Exercise
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class ExerciseRequest(
    @field:JsonProperty("exercises")
    @field:NotNull(message = "Necessário informar os exercícios")
    val exercises: List<Exercise>
)