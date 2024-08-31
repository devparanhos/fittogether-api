package br.com.fitogether.api.core.exception.handler

import br.com.fitogether.api.core.enums.GeneralError
import br.com.fitogether.api.core.error.field.FieldError
import br.com.fitogether.api.core.exception.custom.ValidateCodeException
import br.com.fitogether.api.core.exception.global.GlobalException
import br.com.fitogether.api.core.extension.transformToFieldString
import jakarta.mail.MessagingException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(exception: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<GlobalException> {
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
    fun handleHttpRequestMethodNotSupportedException(exception: HttpRequestMethodNotSupportedException, request: WebRequest): ResponseEntity<GlobalException> {
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
    fun handleSendingEmailException(exception: MessagingException, request: WebRequest): ResponseEntity<GlobalException> {
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
    fun handleSendingEmailException(exception: ValidateCodeException, request: WebRequest): ResponseEntity<GlobalException> {
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
}