package br.com.fitogether.api.infrastructure.database.mapper.preference

import br.com.fitogether.api.infrastructure.database.mapper.gender.toModel
import br.com.fitogether.api.domain.model.Preferences

fun br.com.fitogether.api.infrastructure.database.entity.preference.PreferenceEntity.toModel() : Preferences {
    return Preferences(
        id = this.id,
        startAge = this.startAge,
        endAge = this.endAge,
        radiusDistance = this.radiusDistance,
        genders = this.genders.map { it.toModel() }.toList()
    )
}