package br.com.fitogether.api.domain.dto.response.screens.registration.exercise

import br.com.fitogether.api.domain.model.exercise.Exercise
import com.fasterxml.jackson.annotation.JsonProperty

data class GetRegistrationExerciseScreenResponse(
    @field:JsonProperty("title")
    val title: String = "Quais exercícios você tem interesse",

    @field:JsonProperty("exercises")
    val exercises: List<Exercise>
)
