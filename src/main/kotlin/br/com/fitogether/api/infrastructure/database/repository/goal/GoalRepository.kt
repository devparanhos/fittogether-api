package br.com.fitogether.api.infrastructure.database.repository.goal

import br.com.fitogether.api.infrastructure.database.entity.goal.GoalEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GoalRepository : JpaRepository<GoalEntity, Long>