package br.com.fitogether.api.domain.service.experience

import br.com.fitogether.api.infrastructure.database.mapper.experience.toModel
import br.com.fitogether.api.infrastructure.database.repository.experience.ExperienceRepository
import br.com.fitogether.api.domain.model.experience.Experience
import org.springframework.stereotype.Service

@Service
class ExperienceService(
    private val experienceRepository: ExperienceRepository
) {

    fun getAll() : List<Experience> {
        return experienceRepository.findAll().map { it.toModel() }
    }
}