package br.com.fitogether.api.data.repository.preference

import br.com.fitogether.api.data.entity.preference.PreferenceEntity
import br.com.fitogether.api.data.entity.preference.PreferenceGymEntity
import br.com.fitogether.api.data.repository.SoftDeleteRepository

interface PreferenceGymRepository : SoftDeleteRepository<PreferenceGymEntity, Long> {
    fun findByPreference(preference: PreferenceEntity): List<PreferenceGymEntity>
}