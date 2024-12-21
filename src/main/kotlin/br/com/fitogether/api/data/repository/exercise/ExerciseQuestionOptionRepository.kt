package br.com.fitogether.api.data.repository.exercise

import br.com.fitogether.api.data.entity.exercise.ExerciseQuestionEntity
import br.com.fitogether.api.data.entity.exercise.ExerciseQuestionOptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseQuestionOptionRepository : JpaRepository<ExerciseQuestionOptionEntity, Long> {}