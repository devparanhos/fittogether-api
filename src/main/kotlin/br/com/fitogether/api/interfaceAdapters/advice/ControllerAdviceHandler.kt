package br.com.fitogether.api.core.exception.handler

import br.com.fitogether.api.core.enums.GeneralError
import br.com.fitogether.api.core.error.field.FieldError
import br.com.fitogether.api.core.exception.custom.RuleException
import br.com.fitogether.api.core.exception.custom.ValidateCodeException
import br.com.fitogether.api.core.exception.global.GlobalException
import br.com.fitogether.api.core.extension.transformToFieldString
import jakarta.mail.MessagingException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.web.ErrorResponse
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import javax.security.auth.login.LoginException

data class ErrorResponse(val status: Int, val message: String)

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuleException::class)
    fun handleRuleException(ex: RuleException): ResponseEntity<GlobalException> {
        return ResponseEntity(
            GlobalException(
                statusCode = ex.status.value(),
                message = ex.message ?: "Erro desconhecido.",
                internalCode = "",
                errors = null
            ), ex.status
        )
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<GlobalException> {
        return ResponseEntity(
            GlobalException(
                statusCode = HttpStatus.UNPROCESSABLE_ENTITY.value(),
                message = GeneralError.EV001.message,
                internalCode = GeneralError.EV001.code,
                errors = null
            ),
            HttpStatus.UNPROCESSABLE_ENTITY
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNoValidException(
        exception: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<GlobalException> {
        return ResponseEntity(
            GlobalException(
                statusCode = HttpStatus.UNPROCESSABLE_ENTITY.value(),
                message = GeneralError.EV001.message,
                internalCode = GeneralError.EV001.code,
                errors = exception.bindingResult.fieldErrors.map {
                    FieldError(field = it.field.transformToFieldString(), message = it.defaultMessage)
                }
            ),
            HttpStatus.UNPROCESSABLE_ENTITY
        )
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(
        exception: HttpRequestMethodNotSupportedException,
        request: WebRequest
    ): ResponseEntity<GlobalException> {
        return ResponseEntity(
            GlobalException(
                statusCode = HttpStatus.METHOD_NOT_ALLOWED.value(),
                message = GeneralError.ER001.message,
                internalCode = GeneralError.ER001.code,
                errors = null
            ),
            HttpStatus.METHOD_NOT_ALLOWED
        )
    }

    @ExceptionHandler(MessagingException::class)
    fun handleSendingEmailException(
        exception: MessagingException,
        request: WebRequest
    ): ResponseEntity<GlobalException> {
        return ResponseEntity(
            GlobalException(
                statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = GeneralError.ESM001.message,
                internalCode = GeneralError.ESM001.code,
                errors = null
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(ValidateCodeException::class)
    fun handleSendingEmailException(
        exception: ValidateCodeException,
        request: WebRequest
    ): ResponseEntity<GlobalException> {
        return ResponseEntity(
            GlobalException(
                statusCode = exception.statusCode.value(),
                message = exception.message,
                internalCode = exception.internalCode,
                errors = null
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(LoginException::class)
    fun handleAuthenticationFailedException(
        exception: LoginException,
        request: WebRequest
    ): ResponseEntity<GlobalException> {
        return ResponseEntity(
            GlobalException(
                statusCode = HttpStatus.UNAUTHORIZED.value(),
                message = exception.message,
                internalCode = GeneralError.EAUTH001.code,
                errors = null
            ),
            HttpStatus.UNAUTHORIZED
        )
    }

    @ExceptionHandler(JwtException::class)
    fun handleJwtException(exception: JwtException, request: WebRequest): ResponseEntity<GlobalException> {
        return ResponseEntity(
            GlobalException(
                statusCode = HttpStatus.UNAUTHORIZED.value(),
                message = exception.message,
                internalCode = GeneralError.EAUTH002.code,
                errors = null
            ),
            HttpStatus.UNAUTHORIZED
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleMissingBodyException(exception: HttpMessageNotReadableException): ResponseEntity<GlobalException> {
        return ResponseEntity(
            GlobalException(
                statusCode = HttpStatus.BAD_REQUEST.value(),
                message = GeneralError.EV005.message,
                internalCode = GeneralError.EV005.code,
                errors = null
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}