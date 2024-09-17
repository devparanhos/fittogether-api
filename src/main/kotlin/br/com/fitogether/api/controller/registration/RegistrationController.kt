package br.com.fitogether.api.controller.registration

import br.com.fitogether.api.controller.base.BaseController
import br.com.fitogether.api.domain.dto.request.registration.ExerciseRequest
import br.com.fitogether.api.domain.dto.request.registration.ExperienceRequest
import br.com.fitogether.api.domain.dto.request.registration.GenderRequest
import br.com.fitogether.api.domain.dto.request.registration.GoalRequest
import br.com.fitogether.api.domain.dto.response.screens.registration.gender.GetRegistrationGenderScreenResponse
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.exercise.GetRegistrationExerciseScreenResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.experience.GetRegistrationExperienceScreenResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.goal.GetRegistrationGoalsScreenResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.preference.GetRegistrationPreferenceScreenResponse
import br.com.fitogether.api.domain.service.screen.registration.ScreenRegistrationService
import br.com.fitogether.api.domain.service.user.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/registration")
class RegistrationController(
    private val screenRegistrationService: ScreenRegistrationService,
    private val userService: UserService
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

    @GetMapping(value = ["/screen/preferences"])
    fun getPreferencesScreen() : GetRegistrationPreferenceScreenResponse{
        return execute(
            useCase = { userId ->
                screenRegistrationService.buildPreferenceScreen(userId = userId)
            }
        )
    }
}