package br.com.fitogether.api.config.authentication

import br.com.fitogether.api.core.enums.GeneralError
import br.com.fitogether.api.core.exception.global.GlobalException
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

@Configuration
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
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