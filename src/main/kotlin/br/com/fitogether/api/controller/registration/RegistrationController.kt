package br.com.fitogether.api.controller.registration

import br.com.fitogether.api.controller.base.BaseController
import br.com.fitogether.api.core.exception.custom.RuleException
import br.com.fitogether.api.domain.dto.request.registration.ExerciseRequest
import br.com.fitogether.api.domain.dto.request.registration.ExperienceRequest
import br.com.fitogether.api.domain.dto.request.registration.GenderRequest
import br.com.fitogether.api.domain.dto.request.registration.GoalRequest
import br.com.fitogether.api.domain.dto.request.registration.PreferencesRequest
import br.com.fitogether.api.domain.dto.response.screens.registration.gender.GetRegistrationGenderScreenResponse
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.exercise.GetRegistrationExerciseScreenResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.experience.GetRegistrationExperienceScreenResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.goal.GetRegistrationGoalsScreenResponse
import br.com.fitogether.api.domain.service.screen.registration.ScreenRegistrationService
import br.com.fitogether.api.domain.service.user.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/registration")
class RegistrationController(
    private val screenRegistrationService: ScreenRegistrationService,
    private val userService: UserService,
) : BaseController() {

    @GetMapping("screen/genders")
    fun getGendersScreen(): GetRegistrationGenderScreenResponse {
        return screenRegistrationService.buildGenderScreen()
    }

    @PostMapping("/gender")
    fun setGender(
        @AuthenticationPrincipal userId: Long,
        @RequestBody @Valid genderRequest: GenderRequest
    ): UserResponse {
        return userService.updateGender(genderId = genderRequest.genderId, userId = userId)
    }

    @GetMapping("/screen/goals")
    fun getGoalsScreen(): GetRegistrationGoalsScreenResponse {
        return screenRegistrationService.buildGoalsScreen()
    }

    @PostMapping("/goals")
    fun setGoals(
        @AuthenticationPrincipal userId: Long,
        @RequestBody goalsRequest: GoalRequest
    ): UserResponse {
        return userService.setUserGoals(userId = userId, goals = goalsRequest.goals)
    }

    @GetMapping("/screen/exercises")
    fun getExercisesScreen(): GetRegistrationExerciseScreenResponse {
        return screenRegistrationService.buildExercisesScreen()
    }

    @PostMapping("/exercises")
    fun setExercises(
        @AuthenticationPrincipal userId: Long,
        @RequestBody exercisesRequest: ExerciseRequest
    ): UserResponse {
        return userService.setUserExercises(userId = userId, exercises = exercisesRequest.exercises)
    }

    @GetMapping("/screen/experiences")
    fun getExperiencesScreen(): GetRegistrationExperienceScreenResponse {
        return screenRegistrationService.buildExperienceScreen()
    }

    @PostMapping("/experience")
    fun setExperience(
        @AuthenticationPrincipal userId: Long,
        @RequestBody experienceRequest: ExperienceRequest
    ): UserResponse {
        return userService.setUserExperience(userId = userId, experienceId = experienceRequest.experienceId)
    }

    @Operation(summary = "Salva as preferências de busca de parceiros")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Server internal error")
        ]
    )
    @PostMapping("/preferences")
    fun setPreferences(
        @AuthenticationPrincipal userId: Long,
        @RequestBody preferencesRequest: PreferencesRequest
    ): UserResponse {
        return userService.setUserPreferences(userId = userId, preferences = preferencesRequest)
    }

    @Operation(summary = "Atualiza as preferências de busca de parceiros")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Server internal error")
        ]
    )
    @PutMapping("/preferences")
    fun updatePreferences(
        @AuthenticationPrincipal userId: Long,
        @RequestBody preferencesRequest: PreferencesRequest
    ): UserResponse {
        return userService.updateUserPreferences(userId = userId, preferences = preferencesRequest)
    }

    @PostMapping("/photo")
    fun uploadPhoto(
        @AuthenticationPrincipal userId: Long,
        @RequestParam("file") file: MultipartFile
    ): UserResponse {
        if (file.isEmpty) {
            throw RuleException(HttpStatus.UNPROCESSABLE_ENTITY, "A imagem não pode estar vazia")
        }

        if (!file.contentType?.startsWith("image/")!!) {
            throw RuleException(HttpStatus.UNPROCESSABLE_ENTITY, "O arquivo deve ser uma imagem")
        }

        return userService.uploadPhoto(userId, file)
    }
}