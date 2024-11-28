package br.com.fitogether.api.domain.service.gender

import br.com.fitogether.api.data.mapper.gender.toModel
import br.com.fitogether.api.data.repository.gender.GenderRepository
import br.com.fitogether.api.domain.model.gender.Gender
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class GenderService(
    private val genderRepository: GenderRepository
) {
    fun getAllGenders(): List<Gender> {
        return genderRepository.findAll().map { it.toModel() }
    }

    fun findById(id: Long): Boolean {
        return genderRepository.findById(id).getOrNull() != null
    }
}