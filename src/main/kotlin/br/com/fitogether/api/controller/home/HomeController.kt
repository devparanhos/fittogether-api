package br.com.fitogether.api.controller.home

import br.com.fitogether.api.controller.base.BaseController
import br.com.fitogether.api.domain.dto.request.home.HomeScreenRequest
import br.com.fitogether.api.domain.dto.response.home.screen.HomeScreenResponse
import br.com.fitogether.api.domain.service.home.screen.HomeScreenService
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/home")
class HomeController(
    private val homeScreenService: HomeScreenService,
) : BaseController() {
    @GetMapping()
    fun getHomeScreen(
        @AuthenticationPrincipal userId: Long,
    ): HomeScreenResponse {
        return execute {
            homeScreenService.buildHomeScreen(userId)
        }
    }
}