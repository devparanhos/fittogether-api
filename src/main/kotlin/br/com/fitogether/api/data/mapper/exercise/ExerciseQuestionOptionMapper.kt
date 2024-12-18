package br.com.fitogether.api.data.mapper.exercise

import br.com.fitogether.api.data.entity.exercise.ExerciseQuestionOptionEntity
import br.com.fitogether.api.domain.model.exercise.ExerciseQuestionOption

fun ExerciseQuestionOptionEntity.toModel() = ExerciseQuestionOption(
    id = this.id,
    description = this.description
)