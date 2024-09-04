package br.com.fitogether.api.config.filter

import br.com.fitogether.api.core.enums.GeneralError
import br.com.fitogether.api.domain.service.user.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
class JwtAuthenticationFilter(
    private val jwtDecoder: JwtDecoder,
    private val userService: UserService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)
            try {
                if (userService.isValidToken(token = token)) {
                    val jwt = jwtDecoder.decode(token)
                    val authentication = JwtAuthenticationToken(jwt, null)
                    SecurityContextHolder.getContext().authentication = authentication
                } else {
                    throw JwtException(GeneralError.EAUTH002.message)
                }
            } catch (e: JwtException) {
                throw JwtException(GeneralError.EAUTH002.message)
            }
        }
        filterChain.doFilter(request, response)
    }

}