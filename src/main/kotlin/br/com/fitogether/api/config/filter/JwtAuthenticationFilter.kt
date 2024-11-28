package br.com.fitogether.api.config.filter

import br.com.fitogether.api.core.enums.GeneralError
import br.com.fitogether.api.core.exception.global.GlobalException
import br.com.fitogether.api.data.repository.user.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
class JwtAuthenticationFilter(
    private val jwtDecoder: JwtDecoder,
    private val userRepository: UserRepository
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
                val jwt = jwtDecoder.decode(token)
                val username = jwt.subject
                val user = userRepository.findByUsername(username)

                user?.let {
                    if (it.accessToken == token) {
                        val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                        SecurityContextHolder.getContext().authentication = authentication
                    } else {
                        sendErrorResponse(response = response)
                        return
                    }
                } ?: run {
                    sendErrorResponse(response = response)
                    return
                }
            } catch (e: JwtException) {
                sendErrorResponse(response = response)
                return
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun sendErrorResponse(response: HttpServletResponse) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json"
        response.writer.write(
            ObjectMapper().writeValueAsString(
                GlobalException(
                    statusCode = HttpStatus.UNAUTHORIZED.value(),
                    message = GeneralError.EAUTH002.message,
                    internalCode = GeneralError.EAUTH002.code,
                    errors = null
                )
            )
        )
    }
}