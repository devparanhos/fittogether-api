package br.com.fitogether.api.domain.model.preference

import java.time.LocalTime

data class PreferenceSchedule(
    val id: Long?,
    val dayWeek: Int?,
    val startTime: LocalTime?,
    val endTime: LocalTime?,
)
