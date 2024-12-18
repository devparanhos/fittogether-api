package br.com.fitogether.api.data.repository.exercise

import br.com.fitogether.api.data.entity.exercise.ExerciseQuestionEntity
import br.com.fitogether.api.data.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseQuestionRepository : JpaRepository<ExerciseQuestionEntity, Long> {
    fun findByExerciseId(exerciseId: Long): List<ExerciseQuestionEntity>
}