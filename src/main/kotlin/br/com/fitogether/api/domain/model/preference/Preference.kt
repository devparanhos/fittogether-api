package br.com.fitogether.api.domain.model.preference

import br.com.fitogether.api.domain.model.gender.Gender
import br.com.fitogether.api.domain.model.unit.Unit

data class Preference(
    val id: Long?,
    val startAge: Int,
    val endAge: Int,
    val radiusDistance: Int,
    val genders: List<Gender>,
    val units: List<Unit>?,
    val schedules: List<PreferenceSchedule>?,
)
