package br.com.fitogether.api.domain.service.exercise

import br.com.fitogether.api.core.exception.custom.RuleException
import br.com.fitogether.api.data.entity.exercise.ExercisePoolAnswerEntity
import br.com.fitogether.api.data.entity.exercise.ExercisePoolEntity
import br.com.fitogether.api.data.mapper.exercise.toModel
import br.com.fitogether.api.data.repository.exercise.*
import br.com.fitogether.api.data.repository.user.UserRepository
import br.com.fitogether.api.domain.dto.request.exercise.ExercisePoolRequest
import br.com.fitogether.api.domain.dto.response.exercise.ExerciseQuestionsResponse
import br.com.fitogether.api.domain.model.exercise.Exercise
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ExerciseService(
    private val userRepository: UserRepository,
    private val exerciseRepository: ExerciseRepository,
    private val exerciseQuestionRepository: ExerciseQuestionRepository,
    private val exerciseQuestionOptionRepository: ExerciseQuestionOptionRepository,
    private val exercisePoolRepository: ExercisePoolRepository,
    private val exercisePoolAnswerRepository: ExercisePoolAnswerRepository
) {

    fun getAll(): List<Exercise> {
        return exerciseRepository.findAll().map { it.toModel() }
    }

    fun getExerciseQuestions(exerciseId: Long): ExerciseQuestionsResponse {
        try {
            exerciseRepository.findById(exerciseId).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Exercício não encontrado")
            }

            val questions = exerciseQuestionRepository.findByExerciseId(exerciseId)
            if (questions.isEmpty()) {
                throw RuleException(HttpStatus.NOT_FOUND, "Questões não encontradas")
            }

            return ExerciseQuestionsResponse(questions = questions.map { it.toModel() })

        } catch (exception: Exception) {
            throw exception
        }
    }

    @Transactional
    fun createExercisePool(userId: Long, exerciseId: Long, exercisePoolRequest: ExercisePoolRequest) {
        try {
            val user = userRepository.findById(userId).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
            }

            val exercise = exerciseRepository.findById(exerciseId).orElseThrow {
                RuleException(HttpStatus.NOT_FOUND, "Exercício não encontrado")
            }

            val questions = exerciseQuestionRepository.findByExerciseId(exerciseId)
            if (questions.isEmpty()) {
                throw RuleException(HttpStatus.NOT_FOUND, "Questões não encontradas")
            }

            // Recupera todas as opções válidas relacionadas às questões do exercício
            val validOptions = questions.flatMap { it.options.filterNotNull() }.map { it.id }.toSet()

            // Valida se todas as opções enviadas no request são válidas
            val invalidOptions = exercisePoolRequest.questions.map { it.toLong() }.filter { it !in validOptions }

            if (invalidOptions.isNotEmpty()) {
                throw RuleException(
                    HttpStatus.NOT_FOUND,
                    "As opções ${invalidOptions.joinToString(", ")} não existem para o exercício $exerciseId"
                )
            }

            // Verifica se o usuário já possui um registro na tabela `exercise_pool` para o exercício
            val existingExercisePool = exercisePoolRepository.findPoolStartedByUserAndExercise(user, exercise)

            // Atualiza o registro existente indicando a data de término
            existingExercisePool?.apply {
                endTime = LocalDateTime.now()
            }

            // Cria um novo registro
            val exercisePool = ExercisePoolEntity(
                user = user, exercise = exercise, startTime = LocalDateTime.now(), endTime = null
            )
            exercisePoolRepository.save(exercisePool)

            // Cria os novos registros em `exercise_pool_answer`
            val answers = exercisePoolRequest.questions.map { optionId ->
                val questionOption = exerciseQuestionOptionRepository.findById(optionId.toLong()).orElseThrow {
                    RuleException(HttpStatus.NOT_FOUND, "Opção não encontrada para ID: $optionId")
                }
                ExercisePoolAnswerEntity(
                    exerciseQuestionOption = questionOption, exercisePool = exercisePool
                )
            }
            exercisePoolAnswerRepository.saveAll(answers)

        } catch (exception: Exception) {
            throw exception
        }
    }
}