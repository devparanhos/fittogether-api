package br.com.fitogether.api.domain.service.exercise

import br.com.fitogether.api.data.mapper.exercise.toModel
import br.com.fitogether.api.data.repository.exercise.ExerciseRepository
import br.com.fitogether.api.domain.model.exercise.Exercise
import org.springframework.stereotype.Service

@Service
class ExerciseService(
   private val exerciseRepository: ExerciseRepository
) {

    fun getAll() : List<Exercise> {
         return exerciseRepository.findAll().map { it.toModel() }
    }
}