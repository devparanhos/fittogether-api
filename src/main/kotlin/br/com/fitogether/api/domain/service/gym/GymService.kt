package br.com.fitogether.api.domain.service.gym

import br.com.fitogether.api.data.mapper.gym.toGymResponse
import br.com.fitogether.api.data.mapper.gym.toModel
import br.com.fitogether.api.data.repository.gym.GymRepository
import br.com.fitogether.api.domain.dto.response.GymResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class GymService(
    private val gymRepository: GymRepository
) {
    fun getGyms(lat: Double, lng: Double, radius: Double, exercises: List<String>?): List<GymResponse> {
        if (exercises == null) {
            return gymRepository.findGymsWithinLatLng(
                lat = lat,
                lng = lng,
                radius = radius
            )
                .map { it.toModel().toGymResponse() }
        } else {
            return gymRepository.findGymsWithinLatLngAndExercise(
                lat = lat,
                lng = lng,
                radius = radius,
                exercises = exercises
            )
                .map { it.toModel().toGymResponse() }
        }
    }
}

