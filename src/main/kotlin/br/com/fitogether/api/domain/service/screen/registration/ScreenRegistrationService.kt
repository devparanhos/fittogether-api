package br.com.fitogether.api.domain.service.screen.registration

import br.com.fitogether.api.domain.dto.response.screens.registration.exercise.GetRegistrationExerciseScreenResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.experience.GetRegistrationExperienceScreenResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.gender.GetRegistrationGenderScreenResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.goal.GetRegistrationGoalsScreenResponse
import br.com.fitogether.api.domain.service.exercise.ExerciseService
import br.com.fitogether.api.domain.service.experience.ExperienceService
import br.com.fitogether.api.domain.service.gender.GenderService
import br.com.fitogether.api.domain.service.goal.GoalService

import org.springframework.stereotype.Service

@Service
class ScreenRegistrationService(
    private val genderService: GenderService,
    private val goalService: GoalService,
    private val exerciseService : ExerciseService,
    private val experienceService: ExperienceService
) {
    fun buildGenderScreen() : GetRegistrationGenderScreenResponse {
        return GetRegistrationGenderScreenResponse(
            genders = genderService.getAllGenders()
        )
    }

    fun buildGoalsScreen() : GetRegistrationGoalsScreenResponse {
        return GetRegistrationGoalsScreenResponse(
            goals = goalService.getGoals()
        )
    }

    fun buildExercisesScreen() : GetRegistrationExerciseScreenResponse {
        return GetRegistrationExerciseScreenResponse(
            exercises = exerciseService.getAll()
        )
    }

    fun buildExperienceScreen() : GetRegistrationExperienceScreenResponse {
        return GetRegistrationExperienceScreenResponse(
            experiences = experienceService.getAll()
        )
    }
}