package br.com.fitogether.api.domain.dto.response.screens.registration

import br.com.fitogether.api.domain.model.goal.Goal
import com.fasterxml.jackson.annotation.JsonProperty

data class GetRegistrationGoalsScreenResponse(
    @field:JsonProperty("title")
    val title: String = "Quais os seus objetivos",

    @field:JsonProperty("goals")
    val goals: List<Goal>
)
