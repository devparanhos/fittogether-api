package br.com.fitogether.api.data.repository.experience

import br.com.fitogether.api.data.entity.experience.ExperienceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<ExperienceEntity, Long>