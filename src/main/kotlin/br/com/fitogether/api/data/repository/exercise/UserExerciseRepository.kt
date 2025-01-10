package br.com.fitogether.api.data.repository.exercise

import br.com.fitogether.api.data.entity.exercise.UserExerciseEntity
import br.com.fitogether.api.data.repository.SoftDeleteRepository
import org.springframework.stereotype.Repository

@Repository
interface UserExerciseRepository : SoftDeleteRepository<UserExerciseEntity, Long> {
    fun findByUserId(userId: Long): List<UserExerciseEntity>
}