package br.com.fitogether.api.controller.registration

import br.com.fitogether.api.controller.base.BaseController
import br.com.fitogether.api.domain.dto.request.registration.GenderRequest
import br.com.fitogether.api.domain.dto.request.registration.GoalRequest
import br.com.fitogether.api.domain.dto.response.screens.registration.GetRegistrationGenderScreenResponse
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.dto.response.screens.registration.GetRegistrationGoalsScreenResponse
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
    fun setGoalsScreen(
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
}