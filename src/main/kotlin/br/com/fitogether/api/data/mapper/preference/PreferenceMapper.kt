package br.com.fitogether.api.data.mapper.preference

import br.com.fitogether.api.data.entity.preference.PreferenceEntity
import br.com.fitogether.api.data.mapper.gender.toModel
import br.com.fitogether.api.domain.model.Preferences

fun PreferenceEntity.toModel() : Preferences {
    return Preferences(
        id = this.id,
        startAge = this.startAge,
        endAge = this.endAge,
        radiusDistance = this.radiusDistance,
        genders = this.genders.map { it.toModel() }.toList()
    )
}