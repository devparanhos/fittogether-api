package br.com.fitogether.api.data.mapper.preference

import br.com.fitogether.api.data.entity.preference.PreferenceScheduleEntity
import br.com.fitogether.api.domain.model.preference.PreferenceSchedule

fun PreferenceScheduleEntity.toModel() = PreferenceSchedule(
    id = this.id,
    dayWeek = this.dayWeek,
    startTime = this.startTime,
    endTime = this.endTime
)