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
    genders = this.preferenceGenders.map { it.gender.toModel() },
    gyms = this.preferenceGyms.map { it.gym.toModel() },
    schedules = this.schedules.map { it.toModel() },
)