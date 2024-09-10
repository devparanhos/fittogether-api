package br.com.fitogether.api.domain.service.screen.registration.gender

import br.com.fitogether.api.domain.dto.response.GetRegistrationGenderScreenResponse
import br.com.fitogether.api.domain.service.gender.GenderService

import org.springframework.stereotype.Service

@Service
class RegistrationGenderScreenService(
    private val genderService: GenderService
) {
    fun buildScreen() : GetRegistrationGenderScreenResponse {
        return GetRegistrationGenderScreenResponse(
            genders = genderService.getAllGenders()
        )
    }
}