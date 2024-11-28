package br.com.fitogether.api.data.repository.preference

import br.com.fitogether.api.data.entity.preference.PreferenceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PreferenceRepository : JpaRepository<PreferenceEntity, Long> {}