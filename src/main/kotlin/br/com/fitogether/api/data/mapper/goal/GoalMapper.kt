package br.com.fitogether.api.data.mapper.goal

import br.com.fitogether.api.data.entity.goal.GoalEntity
import br.com.fitogether.api.domain.model.goal.Goal

fun GoalEntity.toModel() : Goal {
    return Goal(
        id = this.id,
        name = this.name?.value,
        icon = this.icon
    )
}