package br.com.fitogether.api.controller.registration

import br.com.fitogether.api.controller.base.BaseController
import br.com.fitogether.api.domain.dto.request.registration.GenderRequest
import br.com.fitogether.api.domain.dto.response.GetRegistrationGenderScreenResponse
import br.com.fitogether.api.domain.dto.response.UserResponse
import br.com.fitogether.api.domain.service.screen.registration.gender.RegistrationGenderScreenService
import br.com.fitogether.api.domain.service.user.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/registration")
class RegistrationController(
    private val registrationGenderScreenService: RegistrationGenderScreenService,
    private val userService: UserService
) : BaseController() {

    @GetMapping(value = ["screen/genders"])
    fun getGendersScreen() : GetRegistrationGenderScreenResponse {
        return execute {
            registrationGenderScreenService.buildScreen()
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
}