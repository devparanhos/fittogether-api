package br.com.fitogether.api.domain.dto.response

import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.domain.model.exercise.Exercise
import br.com.fitogether.api.domain.model.goal.Goal
import br.com.fitogether.api.domain.model.preference.Preference
import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse(
    @field:JsonProperty("id")
    val id: Long?,

    @field:JsonProperty("email")
    val email: String,

    @field:JsonProperty("name")
    val name: String,

    @field:JsonProperty("registration_step")
    val registrationStep: RegistrationStep,

    @field:JsonProperty("photo")
    val photo: String?,

    @field:JsonProperty("username")
    val username: String,

    @field:JsonProperty("gender")
    val gender: String?,

    @field:JsonProperty("goals")
    val goals: List<Goal>,

    @field:JsonProperty("exercises")
    val exercises: List<Exercise>,

    @field:JsonProperty("experience")
    val experience: String?,

    @field:JsonProperty("preference")
    val preference: Preference?,

    @field:JsonProperty("registration_status")
    val registrationStatus: UserRegistrationStatus
)