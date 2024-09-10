package br.com.fitogether.api.domain.dto.response

import br.com.fitogether.api.domain.model.gender.Gender

data class GetRegistrationGenderScreenResponse(
    val title: String = "Qual o seu gênero",
    val genders: List<Gender>
)
