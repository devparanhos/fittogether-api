package br.com.fitogether.api.data.mapper.goal

import br.com.fitogether.api.core.enums.Goals
import br.com.fitogether.api.data.entity.goal.GoalEntity
import br.com.fitogether.api.domain.model.goal.Goal

fun GoalEntity.toModel() : Goal {
    return Goal(
        id = this.id,
        name = this.name?.value,
        icon = this.icon
    )
}

fun Goal.toEntity() : GoalEntity {
    return GoalEntity(
        id = this.id,
        name = Goals.GYM,
        icon = this.icon
    )
}