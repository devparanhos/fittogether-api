package br.com.fitogether.api.controller.registration

import br.com.fitogether.api.controller.base.BaseController
import br.com.fitogether.api.data.entity.user.UserEntity
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
import br.com.fitogether.api.domain.service.aws.S3Service
import br.com.fitogether.api.domain.service.screen.registration.ScreenRegistrationService
import br.com.fitogether.api.domain.service.user.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/registration")
class RegistrationController(
    private val screenRegistrationService: ScreenRegistrationService,
    private val userService: UserService,
    private val s3Service: S3Service
) : BaseController() {

    @GetMapping(value = ["screen/genders"])
    fun getGendersScreen() : GetRegistrationGenderScreenResponse {
        return execute {
            screenRegistrationService.buildGenderScreen()
        }
    }

    @PostMapping(value = ["{userId}/gender"])
    fun setGender(
        @PathVariable("userId") userId: Long,
        @RequestBody @Valid genderRequest: GenderRequest
    ) : UserResponse {
        return execute(
            userId = userId,
            useCase = {
                userService.updateGender(genderId = genderRequest.genderId, userId = userId)
            }
        )
    }

    @GetMapping(value = ["/screen/goals"])
    fun getGoalsScreen() : GetRegistrationGoalsScreenResponse {
        return execute(
            useCase = {
                screenRegistrationService.buildGoalsScreen()
            }
        )
    }

    @PostMapping(value = ["{userId}/goals"])
    fun setGoals(
        @PathVariable("userId") userId: Long,
        @RequestBody goalsRequest: GoalRequest
    ) : UserResponse {
        return execute(
            userId = userId,
            useCase = {
                userService.setUserGoals(userId = userId, goals = goalsRequest.goals)
            }
        )
    }

    @GetMapping(value = ["/screen/exercises"])
    fun getExercisesScreen() : GetRegistrationExerciseScreenResponse {
        return execute(
            useCase = {
                screenRegistrationService.buildExercisesScreen()
            }
        )
    }

    @PostMapping(value = ["{userId}/exercises"])
    fun setExercises(
        @PathVariable("userId") userId: Long,
        @RequestBody exercisesRequest: ExerciseRequest
    ) : UserResponse {
        return execute(
            userId = userId,
            useCase = {
                userService.setUserExercises(userId = userId, exercises = exercisesRequest.exercises)
            }
        )
    }

    @GetMapping(value = ["/screen/experiences"])
    fun getExperiencesScreen() : GetRegistrationExperienceScreenResponse {
        return execute(
            useCase = {
                screenRegistrationService.buildExperienceScreen()
            }
        )
    }

    @PostMapping(value = ["{userId}/experience"])
    fun setExperience(
        @PathVariable("userId") userId: Long,
        @RequestBody experienceRequest: ExperienceRequest
    ) : UserResponse {
        return execute(
            userId = userId,
            useCase = {
                userService.setUserExperience(userId = userId, experienceId = experienceRequest.experienceId)
            }
        )
    }

    @Operation(summary = "Salva as preferências de busca de parceiros")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Server internal error")
        ]
    )
    @PostMapping(value = ["{userId}/preferences"])
    fun setPreferences(
        @PathVariable("userId") userId: Long,
        @RequestBody preferencesRequest: PreferencesRequest
    ) : UserResponse {
        return execute(
            userId = userId,
            useCase = {
                userService.setUserPreferences(userId = userId, preferences = preferencesRequest)
            }
        )
    }

    @Operation(summary = "Atualiza as preferências de busca de parceiros")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Server internal error")
        ]
    )
    @PutMapping(value = ["{userId}/preferences"])
    fun updatePreferences(
        @PathVariable("userId") userId: Long,
        @RequestBody preferencesRequest: PreferencesRequest
    ) : UserResponse {
        return execute(
            userId = userId,
            useCase = {
                userService.updateUserPreferences(userId = userId, preferences = preferencesRequest)
            }
        )
    }

    @PostMapping(value = ["{userId}/photo"])
    fun uploadPhoto(
        @PathVariable userId: Long,
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<String> {
        if (file.isEmpty) {
            return ResponseEntity.badRequest().body("A imagem não pode estar vazia")
        }

        if (!file.contentType?.startsWith("image/")!!) {
            return ResponseEntity.badRequest().body("O arquivo enviado não é uma imagem")
        }

        return execute(
            userId = userId,
            useCase = {
                s3Service.uploadFile(file, userId)
            }
        )
    }
}