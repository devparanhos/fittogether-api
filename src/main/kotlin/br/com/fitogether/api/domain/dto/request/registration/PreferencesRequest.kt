package br.com.fitogether.api.domain.dto.request.registration

import br.com.fitogether.api.domain.model.gender.Gender
import br.com.fitogether.api.domain.model.gym.Gym
import br.com.fitogether.api.domain.validation.gender.annotation.GenderExists
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Range
import java.time.LocalTime

data class PreferencesRequest(
    @field:JsonProperty("genders_id")
    @field:JsonAlias("genders_id")
    @field:NotNull(message = "Você precisa informar um gênero")
    @GenderExists
    val genders: List<Gender>,

    @field:JsonProperty("radius_distance")
    @field:JsonAlias("radius_distance")
    @field:NotNull(message = "Você precisa informar a distância máxima desejada")
    val radiusDistance: Int,

    @field:JsonProperty("start_age")
    @field:JsonAlias("start_age")
    @field:NotNull(message = "Você precisa informar a idade mínima da faixa etária")
    val startAge: Int,

    @field:JsonProperty("end_age")
    @field:JsonAlias("end_age")
    @field:NotNull(message = "Você precisa informar a idade máxima da faixa etária")
    val endAge: Int,

    @field:JsonProperty("schedule")
    @field:JsonAlias("schedule")
    @field:NotEmpty(message = "Você precisa informar os dias e horários de preferência")
    @field:Size(min = 1, message = "Você precisa informar os dias e horários de preferência")
    val schedule: List<@Valid ScheduleItem>,

    @field:JsonProperty("gyms_id")
    @field:JsonAlias("gyms_id")
    @field:NotNull(message = "Você precisa informar a(s) academia(s) que você gosta de frequentar")
    val gyms: List<Gym>,
)

data class ScheduleItem(
    @field:JsonProperty("day")
    @field:JsonAlias("day")
    @field:Range(min = 0, max = 6, message = "Informe o dia da semana de preferência. Entre 0 (segunda) e 6 (domingo)")
    val day: Int,

    @field:JsonProperty("start_time")
    @field:JsonAlias("start_time")
    @field:NotNull(message = "O campo 'start_time' deve estar no formato HH:mm")
    val startTime: LocalTime,

    @field:JsonProperty("end_time")
    @field:JsonAlias("end_time")
    @field:NotNull(message = "O campo 'end_time' deve estar no formato HH:mm")
    val endTime: LocalTime
)
