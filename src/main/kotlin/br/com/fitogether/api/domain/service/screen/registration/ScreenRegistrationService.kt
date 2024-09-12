package br.com.fitogether.api.domain.service.screen.registration

import br.com.fitogether.api.domain.dto.response.screens.registration.GetRegistrationGenderScreenResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.GetRegistrationGoalsScreenResponse
import br.com.fitogether.api.domain.service.gender.GenderService
import br.com.fitogether.api.domain.service.goal.GoalService

import org.springframework.stereotype.Service

@Service
class ScreenRegistrationService(
    private val genderService: GenderService,
    private val goalService: GoalService
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
}