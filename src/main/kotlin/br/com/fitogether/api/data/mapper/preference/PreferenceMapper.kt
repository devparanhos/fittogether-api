package br.com.fitogether.api.data.mapper.preference

import br.com.fitogether.api.data.entity.preference.PreferenceEntity
import br.com.fitogether.api.data.mapper.gender.toModel
import br.com.fitogether.api.data.mapper.gym.toModel
import br.com.fitogether.api.domain.model.preference.Preference

fun PreferenceEntity.toModel() = Preference(
    id = this.id,
    startAge = this.startAge,
    endAge = this.endAge,
    radiusDistance = this.radiusDistance,
    genders = this.genders.map { it.toModel() },
    gyms = this.gyms.map { it.toModel() },
    schedules = this.schedules.map { it.toModel() },
)