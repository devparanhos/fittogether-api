package br.com.fitogether.api.domain.dto.response.screens.registration.preference

import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.screens.components.cards.CardsResponse
import br.com.fitogether.api.domain.model.gender.Gender
import br.com.fitogether.api.domain.model.gym.Gym
import com.fasterxml.jackson.annotation.JsonProperty

data class GetRegistrationPreferenceScreenResponse(
    @field:JsonProperty("title")
    val title: String = "Preferências do parceiro",

    @field:JsonProperty("subtitle")
    val subtitle: String = "Conta para gente um pouco sobre suas prefêrencias de parceiro. Não se preocupe você poderá editar depois se quiser",

    @field:JsonProperty("card_gender")
    val cardGender: CardsResponse<Gender>,

    @field:JsonProperty("card_distance")
    val cardDistance: CardsResponse<Int>,

    @field:JsonProperty("card_age")
    val cardAge: CardsResponse<Int>,

    @field:JsonProperty("card_gym")
    val cardGym: CardsResponse<Gym>,

    @field:JsonProperty("user")
    val user: UserResponse
)
