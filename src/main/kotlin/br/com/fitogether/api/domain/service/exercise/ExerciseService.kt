package br.com.fitogether.api.domain.service.exercise

import br.com.fitogether.api.core.exception.custom.RuleException
import br.com.fitogether.api.data.mapper.exercise.toModel
import br.com.fitogether.api.data.mapper.user.toModel
import br.com.fitogether.api.data.mapper.user.toUserResponse
import br.com.fitogether.api.data.repository.exercise.ExerciseQuestionRepository
import br.com.fitogether.api.data.repository.exercise.ExerciseRepository
import br.com.fitogether.api.domain.dto.response.exercise.ExerciseQuestionsResponse
import br.com.fitogether.api.domain.model.exercise.Exercise
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ExerciseService(
    private val exerciseRepository: ExerciseRepository,
    private val exerciseQuestionRepository: ExerciseQuestionRepository
) {

    fun getAll(): List<Exercise> {
        return exerciseRepository.findAll().map { it.toModel() }
    }

    fun getExerciseQuestions(exerciseId: Long): ExerciseQuestionsResponse {
        try {
            exerciseRepository.findById(exerciseId).orElseThrow{
                RuleException(HttpStatus.NOT_FOUND, "Exercício não encontrado")
            }

            val questions = exerciseQuestionRepository.findByExerciseId(exerciseId);
            if (questions.isEmpty()) {
                throw RuleException(HttpStatus.NOT_FOUND, "Questões não encontradas")
            }

            return ExerciseQuestionsResponse(questions = questions.map { it.toModel() })

        } catch (exception: Exception) {
            throw exception
        }
    }
}