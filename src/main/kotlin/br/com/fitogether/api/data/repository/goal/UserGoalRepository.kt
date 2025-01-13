package br.com.fitogether.api.data.repository.goal

import br.com.fitogether.api.data.entity.goal.UserGoalEntity
import br.com.fitogether.api.data.repository.SoftDeleteRepository

interface UserGoalRepository : SoftDeleteRepository<UserGoalEntity, Long> {
    fun findByUserId(userId: Long): List<UserGoalEntity>
}