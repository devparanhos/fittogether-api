package br.com.fitogether.api.domain.service.goal

import br.com.fitogether.api.infrastructure.database.mapper.goal.toModel
import br.com.fitogether.api.infrastructure.database.repository.goal.GoalRepository
import br.com.fitogether.api.domain.model.goal.Goal
import org.springframework.stereotype.Service

@Service
class GoalService(
    private val goalRepository: GoalRepository
) {

    fun getGoals(): List<Goal> {
        return goalRepository.findAll().map { it.toModel() }
    }
}