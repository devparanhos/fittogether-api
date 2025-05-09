package br.com.fitogether.api.domain.service.home.screen

import br.com.fitogether.api.core.exception.custom.RuleException
import br.com.fitogether.api.data.entity.user.UserEntity
import br.com.fitogether.api.data.mapper.exercise.toModel
import br.com.fitogether.api.data.mapper.gym.toModel
import br.com.fitogether.api.data.mapper.user.toProfileModel
import br.com.fitogether.api.data.repository.exercise.ExerciseRepository
import br.com.fitogether.api.data.repository.gym.GymRepository
import br.com.fitogether.api.data.repository.user.UserRepository
import br.com.fitogether.api.domain.dto.request.home.HomeScreenRequest
import br.com.fitogether.api.domain.dto.response.home.screen.HomeScreenResponse
import br.com.fitogether.api.domain.model.gym.Gym
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class HomeScreenService(
    private val userRepository: UserRepository,
    private val gymRepository: GymRepository,
    private val exerciseRepository: ExerciseRepository
) {
    fun buildHomeScreen(userId: Long, payload: HomeScreenRequest): HomeScreenResponse {

        val user = userRepository.findById(userId).orElseThrow {
            RuleException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        }

        val gyms: List<Gym>
        if (payload.exercise == null) {
            gyms = gymRepository.findGymsWithinLatLng(
                lat = payload.lat,
                lng = payload.lng,
                radius = payload.radius
            )
                .map { it.toModel() }
        } else {
            gyms = gymRepository.findGymsWithinLatLngAndExercise(
                lat = payload.lat,
                lng = payload.lng,
                radius = payload.radius,
                exercises = payload.exercise
            )
                .map { it.toModel() }
        }

        val exercises = exerciseRepository.findAll().map { it.toModel() }

        return HomeScreenResponse(
            profile = user.toProfileModel(),
            exercises = exercises,
            gyms = gyms,
        )
    }
}