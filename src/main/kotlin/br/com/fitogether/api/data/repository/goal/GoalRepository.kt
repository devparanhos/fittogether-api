package br.com.fitogether.api.data.repository.goal

import br.com.fitogether.api.data.entity.goal.GoalEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GoalRepository : JpaRepository<GoalEntity, Long>