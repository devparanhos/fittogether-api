package br.com.fitogether.api.controller.exercises

import br.com.fitogether.api.domain.dto.request.exercise.ExercisePoolRequest
import br.com.fitogether.api.domain.dto.response.MessageResponse
import br.com.fitogether.api.domain.dto.response.exercise.ExerciseQuestionsResponse
import br.com.fitogether.api.domain.service.exercise.ExerciseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/exercise")
class ExerciseController(
    private val exerciseService: ExerciseService
) {
    @GetMapping(value = ["{exerciseId}/questions"])
    fun getExerciseQuestions(
        @AuthenticationPrincipal userId: Long,
        @PathVariable("exerciseId") exerciseId: Long
    ): ExerciseQuestionsResponse {
        return exerciseService.getExerciseQuestions(exerciseId)
    }

    @PostMapping(value = ["{exerciseId}/pool"])
    fun createExercisePool(
        @AuthenticationPrincipal userId: Long,
        @PathVariable("exerciseId") exerciseId: Long,
        @RequestBody @Valid exercisePoolRequest: ExercisePoolRequest,
    ): ResponseEntity<MessageResponse> {
        exerciseService.createExercisePool(userId, exerciseId, exercisePoolRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageResponse("Questionário salvo com sucesso"))
    }
}