package br.com.fitogether.api.interfaceAdapters.controller.base

import br.com.fitogether.api.application.service.AuthenticationService
import br.com.fitogether.api.core.enums.GeneralError

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException

open class BaseController(
    @Autowired private val authenticationService: AuthenticationService
) {
    fun <T>execute(userId: Long? = null, useCase: (userId: Long?) -> T) : T {
        try {
            if (authenticationService.hasPermission(userId = userId)) {
                return useCase(userId)
            } else {
                throw AccessDeniedException(GeneralError.EAUTH003.message)
            }
        } catch (exception: AccessDeniedException) {
            throw exception
        } catch (exception: Exception) {
            throw Exception(GeneralError.EGEN001.message)
        }
    }
}