package br.com.fitogether.api.data.repository.exercise

import br.com.fitogether.api.data.entity.exercise.ExercisePoolAnswerEntity
import br.com.fitogether.api.data.entity.exercise.ExercisePoolAnswerId
import org.springframework.data.jpa.repository.JpaRepository

interface ExercisePoolAnswerRepository : JpaRepository<ExercisePoolAnswerEntity, ExercisePoolAnswerId> {}