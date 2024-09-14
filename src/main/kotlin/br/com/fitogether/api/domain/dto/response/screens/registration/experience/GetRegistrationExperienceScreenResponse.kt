package br.com.fitogether.api.domain.dto.response.screens.registration.experience

import br.com.fitogether.api.domain.model.experience.Experience
import com.fasterxml.jackson.annotation.JsonProperty

data class GetRegistrationExperienceScreenResponse(
    @field:JsonProperty("title")
    val title: String = "Qual sua experiência com atividades físicas",

    @field:JsonProperty("experience")
    val experiences: List<Experience>
)
