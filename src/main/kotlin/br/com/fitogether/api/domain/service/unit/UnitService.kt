package br.com.fitogether.api.domain.service.unit

import br.com.fitogether.api.data.mapper.unit.toModel
import br.com.fitogether.api.data.mapper.unit.toUnitResponse
import br.com.fitogether.api.data.repository.unit.UnitRepository
import br.com.fitogether.api.domain.dto.response.UnitResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UnitService(
    private val unitRepository: UnitRepository
) {
    fun getUnits(lat: Double, lng: Double, radius: Double, exerciseIds: List<Long>?): List<UnitResponse> {
        return if (exerciseIds == null || exerciseIds.isEmpty()) {
            unitRepository.findUnitsWithinLatLng(lat = lat, lng = lng, radius = radius)
        } else {
            unitRepository.findUnitsWithinLatLngAndExercise(
                lat = lat,
                lng = lng,
                radius = radius,
                exerciseIds = exerciseIds
            )
        }.map { it.toModel().toUnitResponse() }
    }
}
