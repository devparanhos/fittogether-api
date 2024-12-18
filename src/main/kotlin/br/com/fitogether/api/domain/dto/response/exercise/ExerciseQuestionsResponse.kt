package br.com.fitogether.api.domain.dto.response.exercise

import br.com.fitogether.api.domain.model.exercise.ExerciseQuestion
import com.fasterxml.jackson.annotation.JsonProperty

data class ExerciseQuestionsResponse(
    @field:JsonProperty("questions")
    val questions: List<ExerciseQuestion>
) {}
