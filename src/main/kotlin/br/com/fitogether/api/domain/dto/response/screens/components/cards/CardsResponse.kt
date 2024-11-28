package br.com.fitogether.api.domain.dto.response.screens.components.cards

import br.com.fitogether.api.domain.model.gender.Gender
import br.com.fitogether.api.domain.model.gym.Gym
import com.fasterxml.jackson.annotation.JsonProperty

data class CardsResponse<T>(
    @field:JsonProperty("title")
    val title: String,

    @field:JsonProperty("info")
    val info: String,

    @field:JsonProperty("description")
    val description: String? = null,

    @field:JsonProperty("data")
    val data: List<T>? = null
) {
    companion object {
        val cardGenderRegistration = CardsResponse<Gender>(
            title = "Gênero",
            info = "Teste"
        )

        val cardDistanceRegistration = CardsResponse<Int>(
            title = "Distância máxima",
            info = "Teste",
            data = listOf(1, 50)
        )

        val cardAgeRegistration = CardsResponse<Int>(
            title = "Faixa etária",
            info = "Teste",
            data = listOf(16, 70)
        )

        val cardGymRegistration = CardsResponse<Gym>(
            title = "Academia",
            info = "Teste",
            description = "Teste"
        )
    }
}
