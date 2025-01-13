package br.com.fitogether.api.data.repository.preference

import br.com.fitogether.api.data.entity.preference.PreferenceEntity
import br.com.fitogether.api.data.entity.preference.PreferenceGenderEntity
import br.com.fitogether.api.data.repository.SoftDeleteRepository

interface PreferenceGenderRepository : SoftDeleteRepository<PreferenceGenderEntity, Long> {
    fun findByPreference(preference: PreferenceEntity): List<PreferenceGenderEntity>
}