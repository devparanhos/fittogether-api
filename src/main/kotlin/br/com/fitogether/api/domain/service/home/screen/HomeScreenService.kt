package br.com.fitogether.api.domain.service.home.screen

import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.exception.custom.RuleException
import br.com.fitogether.api.data.mapper.calltoaction.toModelList
import br.com.fitogether.api.data.mapper.exercise.toModel
import br.com.fitogether.api.data.mapper.unit.toModel
import br.com.fitogether.api.data.mapper.unit.toUnitResponse
import br.com.fitogether.api.data.mapper.user.toProfileModel
import br.com.fitogether.api.data.repository.calltoaction.CallToActionRepository
import br.com.fitogether.api.data.repository.exercise.ExerciseRepository
import br.com.fitogether.api.data.repository.unit.UnitRepository
import br.com.fitogether.api.data.repository.user.UserRepository
import br.com.fitogether.api.domain.dto.response.home.screen.HomeScreenResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class HomeScreenService(
    private val userRepository: UserRepository,
    private val exerciseRepository: ExerciseRepository,
    private val unitRepository: UnitRepository,
    private val callToActionRepository: CallToActionRepository
) {
    companion object {
        private const val HOME_UNITS_RADIUS_METERS = 5000.0 // 5km
    }

    fun buildHomeScreen(userId: Long, lat: Double?, lng: Double?): HomeScreenResponse {

        val user = userRepository.findById(userId).orElseThrow {
            RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        }

        val exercises = exerciseRepository.findAll().map { it.toModel() }

        val units = if (lat != null && lng != null) {
            unitRepository.findUnitsWithinLatLng(
                lat = lat,
                lng = lng,
                radius = HOME_UNITS_RADIUS_METERS
            ).map { it.toModel().toUnitResponse() }
        } else {
            emptyList()
        }

        val callToActions = if (user.registrationStep != RegistrationStep.FINISHED) {
            callToActionRepository.findByTypeAndDeletedAtIsNull("FINISH_REGISTRATION").toModelList()
        } else {
            emptyList()
        }

        return HomeScreenResponse(
            profile = user.toProfileModel(),
            exercises = exercises,
            units = units,
            callToActions = callToActions
        )
    }
}