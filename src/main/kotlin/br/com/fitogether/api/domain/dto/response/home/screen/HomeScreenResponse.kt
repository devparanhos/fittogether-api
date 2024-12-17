package br.com.fitogether.api.domain.dto.response.home.screen

import br.com.fitogether.api.domain.model.exercise.Exercise
import br.com.fitogether.api.domain.model.gym.Gym
import br.com.fitogether.api.domain.model.user.Profile
import com.fasterxml.jackson.annotation.JsonProperty

data class HomeScreenResponse(
    @field:JsonProperty("profile")
    val profile: Profile,

    @field:JsonProperty("exercises")
    val exercises: List<Exercise>?,

    @field:JsonProperty("gyms")
    val gyms: List<Gym>
)
