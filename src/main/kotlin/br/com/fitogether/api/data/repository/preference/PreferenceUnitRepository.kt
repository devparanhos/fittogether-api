package br.com.fitogether.api.data.repository.preference

import br.com.fitogether.api.data.entity.preference.PreferenceEntity
import br.com.fitogether.api.data.entity.preference.PreferenceUnitEntity
import br.com.fitogether.api.data.repository.SoftDeleteRepository

interface PreferenceUnitRepository : SoftDeleteRepository<PreferenceUnitEntity, Long> {
    fun findByPreference(preference: PreferenceEntity): List<PreferenceUnitEntity>
}
