package br.com.fitogether.api.infrastructure.database.mapper.exercise

import br.com.fitogether.api.infrastructure.database.entity.exercise.ExerciseEntity
import br.com.fitogether.api.domain.model.exercise.Exercise

fun ExerciseEntity.toModel(): Exercise {
    return Exercise(
        id = this.id,
        name = this.name?.value,
        icon = this.icon
    )
}