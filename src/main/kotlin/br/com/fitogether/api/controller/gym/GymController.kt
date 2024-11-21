package br.com.fitogether.api.controller.gym

import br.com.fitogether.api.domain.dto.request.gym.GetGymsRequest
import br.com.fitogether.api.domain.dto.response.GymResponse
import br.com.fitogether.api.domain.service.gym.GymService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/gym")
class GymController(
    private val gymService: GymService
) {
    @Operation(summary = "Retorna lista de academias próximas")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Server internal error")
        ]
    )
    @GetMapping
    fun getGyms(
        @Valid getGymsRequest: GetGymsRequest
    ): List<GymResponse> {
        return gymService.getGyms(getGymsRequest.lat!!, getGymsRequest.lng!!)
    }
}