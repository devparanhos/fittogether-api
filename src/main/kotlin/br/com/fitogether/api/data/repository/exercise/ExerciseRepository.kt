package br.com.fitogether.api.data.repository.exercise

import br.com.fitogether.api.data.entity.exercise.ExerciseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<ExerciseEntity, Long>