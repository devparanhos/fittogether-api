package br.com.fitogether.api.controller.unit

import br.com.fitogether.api.controller.base.BaseController
import br.com.fitogether.api.domain.dto.request.unit.GetUnitsRequest
import br.com.fitogether.api.domain.dto.response.UnitResponse
import br.com.fitogether.api.domain.service.unit.UnitService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/units")
class UnitController(
    private val unitService: UnitService
) : BaseController() {
    @Operation(summary = "Retorna lista de unidades próximas")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Server internal error")
        ]
    )
    @GetMapping
    fun getUnits(
        @Valid getUnitsRequest: GetUnitsRequest
    ): List<UnitResponse> {
        val exerciseIds = getUnitsRequest.exercise?.mapNotNull { it.toLongOrNull() }.orEmpty()
        return unitService.getUnits(
            getUnitsRequest.lat!!,
            getUnitsRequest.lng!!,
            getUnitsRequest.radius,
            exerciseIds.ifEmpty { null }
        )
    }
}
