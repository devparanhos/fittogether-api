package br.com.fitogether.api.data.repository.exercise

import br.com.fitogether.api.data.entity.exercise.ExerciseEntity
import br.com.fitogether.api.data.entity.exercise.ExercisePoolEntity
import br.com.fitogether.api.data.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ExercisePoolRepository : JpaRepository<ExercisePoolEntity, Long> {
    fun findByUserAndExercise(user: UserEntity, exercise: ExerciseEntity): List<ExercisePoolEntity?>

    @Query(
        """
        SELECT e FROM exercise_pool e 
        WHERE 
        e.user = :user
        AND e.exercise = :exercise
        AND e.endTime IS NULL
        ORDER BY e.id DESC
        LIMIT 1
        """
    )
    fun findPoolStartedByUserAndExercise(
        @Param("user") user: UserEntity,
        @Param("exercise") exercise: ExerciseEntity
    ): ExercisePoolEntity?
}