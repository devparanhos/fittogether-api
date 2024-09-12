package br.com.fitogether.api.domain.dto.request.registration

import br.com.fitogether.api.domain.model.goal.Goal

data class GoalRequest(
    val goals: List<Goal>
)