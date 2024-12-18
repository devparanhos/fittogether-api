package br.com.fitogether.api.data.mapper.exercise

import br.com.fitogether.api.data.entity.exercise.ExerciseQuestionEntity
import br.com.fitogether.api.domain.model.exercise.ExerciseQuestion

fun ExerciseQuestionEntity.toModel() = ExerciseQuestion(
    id = this.id,
    type = this.type.value,
    description = this.description,
    options = this.options.map { it?.toModel() }
)