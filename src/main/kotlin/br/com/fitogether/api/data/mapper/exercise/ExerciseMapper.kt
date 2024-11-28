package br.com.fitogether.api.data.mapper.exercise

import br.com.fitogether.api.data.entity.exercise.ExerciseEntity
import br.com.fitogether.api.domain.model.exercise.Exercise

fun ExerciseEntity.toModel(): Exercise {
    return Exercise(
        id = this.id,
        name = this.name?.value,
        icon = this.icon
    )
}