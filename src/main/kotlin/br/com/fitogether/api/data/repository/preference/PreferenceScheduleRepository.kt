package br.com.fitogether.api.data.repository.preference

import br.com.fitogether.api.data.entity.preference.PreferenceEntity
import br.com.fitogether.api.data.entity.preference.PreferenceScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PreferenceScheduleRepository : JpaRepository<PreferenceScheduleEntity, Long> {
    fun deleteByPreference(preference: PreferenceEntity)
}