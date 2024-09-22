package br.com.fitogether.api.infrastructure.database.repository.experience

import br.com.fitogether.api.infrastructure.database.entity.experience.ExperienceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<ExperienceEntity, Long>