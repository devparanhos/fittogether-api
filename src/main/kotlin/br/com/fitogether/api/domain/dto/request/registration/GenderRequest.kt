package br.com.fitogether.api.domain.dto.request.registration

import br.com.fitogether.api.interfaceAdapters.validation.gender.annotation.GenderExists
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class GenderRequest(
    @field:JsonProperty("gender_id")
    @field:JsonAlias("gender_id")
    @field:NotNull(message = "Você precisa informar um gênero")
    @br.com.fitogether.api.interfaceAdapters.validation.gender.annotation.GenderExists
    val genderId: Long
)