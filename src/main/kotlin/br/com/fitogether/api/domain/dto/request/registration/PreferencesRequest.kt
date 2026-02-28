package br.com.fitogether.api.domain.dto.request.registration

import br.com.fitogether.api.domain.model.gender.Gender
import br.com.fitogether.api.domain.model.unit.Unit
import br.com.fitogether.api.domain.validation.gender.annotation.GenderExists
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Range
import java.time.LocalTime

data class GenderIdRequest(
    @Schema(
        description = "Identificador único do gênero",
        example = "1"
    )
    val id: Long
)

data class UnitIdRequest(
    @Schema(
        description = "Identificador único da unidade (unit)",
        example = "1"
    )
    val id: Long
)


data class PreferencesRequest(
    @field:JsonProperty("genders_id")
    @field:JsonAlias("genders_id")
    @field:NotNull(message = "Você precisa informar um gênero")
    @GenderExists
    @Schema(
        description = "Lista de IDs de gêneros",
        example = "[{\"id\": 1}, {\"id\": 2}]"
    )
    val genders: List<GenderIdRequest>,

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

    @field:JsonProperty("units_id")
    @field:JsonAlias("units_id", "stores_id", "gyms_id")
    @field:NotNull(message = "Você precisa informar a(s) unidade(s) que você gosta de frequentar")
    @Schema(
        description = "Lista de IDs de unidades (units)",
        example = "[{\"id\": 1}, {\"id\": 2}]"
    )
    val units: List<UnitIdRequest>,
)

data class ScheduleItem(
    @field:JsonProperty("day")
    @field:JsonAlias("day")
    @field:Range(min = 0, max = 6, message = "Informe o dia da semana de preferência. Entre 0 (segunda) e 6 (domingo)")
    val day: Int,

    @field:JsonProperty("start_time")
    @field:JsonAlias("start_time")
    @field:NotNull(message = "O campo 'start_time' deve estar no formato HH:mm")
    @Schema(
        description = "Hora de início no formato HH:mm",
        example = "08:30",
        type = "string"
    )
    val startTime: LocalTime,

    @field:JsonProperty("end_time")
    @field:JsonAlias("end_time")
    @field:NotNull(message = "O campo 'end_time' deve estar no formato HH:mm")
    @Schema(
        description = "Hora fim no formato HH:mm",
        example = "08:30",
        type = "string"
    )
    val endTime: LocalTime
)
