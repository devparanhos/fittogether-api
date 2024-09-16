package br.com.fitogether.api.domain.dto.request.registration

import com.fasterxml.jackson.annotation.JsonProperty

data class ExperienceRequest(
    @field:JsonProperty("experience_id")
    val experienceId: Long
)
