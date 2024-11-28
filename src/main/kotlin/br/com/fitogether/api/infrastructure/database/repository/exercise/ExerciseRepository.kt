package br.com.fitogether.api.infrastructure.database.repository.exercise

import br.com.fitogether.api.infrastructure.database.entity.exercise.ExerciseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<ExerciseEntity, Long>