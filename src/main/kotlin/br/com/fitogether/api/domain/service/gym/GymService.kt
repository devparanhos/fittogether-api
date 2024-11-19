package br.com.fitogether.api.domain.service.gym

import br.com.fitogether.api.data.mapper.gym.toGymResponse
import br.com.fitogether.api.data.mapper.gym.toModel
import br.com.fitogether.api.data.repository.gym.GymRepository
import br.com.fitogether.api.domain.dto.request.gym.GetGymsRequest
import br.com.fitogether.api.domain.dto.response.GymResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class GymService(
    private val gymRepository: GymRepository
) {
    fun getGyms(): List<GymResponse> {
        val gyms = gymRepository.findAll().map { it.toModel().toGymResponse() }
        return gyms
    }
}

