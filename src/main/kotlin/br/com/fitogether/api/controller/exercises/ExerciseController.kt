package br.com.fitogether.api.controller.exercises

import br.com.fitogether.api.data.entity.user.UserEntity
import br.com.fitogether.api.domain.dto.request.exercise.ExercisePoolRequest
import br.com.fitogether.api.domain.dto.response.exercise.ExerciseQuestionsResponse
import br.com.fitogether.api.domain.service.exercise.ExerciseService
import jakarta.validation.Valid
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
        @AuthenticationPrincipal user: UserEntity,
        @PathVariable("exerciseId") exerciseId: Long
    ): ExerciseQuestionsResponse {
        return exerciseService.getExerciseQuestions(exerciseId)
    }

    @PostMapping(value = ["{exerciseId}/pool"])
    fun createExercisePool(
        @AuthenticationPrincipal user: UserEntity,
        @PathVariable("exerciseId") exerciseId: Long,
        @RequestBody @Valid exercisePoolRequest: ExercisePoolRequest,
    ) {
        return exerciseService.createExercisePool(user, exerciseId, exercisePoolRequest)
    }
}