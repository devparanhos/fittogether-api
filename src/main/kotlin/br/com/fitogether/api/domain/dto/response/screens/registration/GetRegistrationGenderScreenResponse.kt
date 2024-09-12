package br.com.fitogether.api.domain.dto.response.screens.registration

import br.com.fitogether.api.domain.model.gender.Gender
import com.fasterxml.jackson.annotation.JsonProperty

data class GetRegistrationGenderScreenResponse(
    @field:JsonProperty("title")
    val title: String = "Qual o seu gênero",

    @field:JsonProperty("genders")
    val genders: List<Gender>
)
