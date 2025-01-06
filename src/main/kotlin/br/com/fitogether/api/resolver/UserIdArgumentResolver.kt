package br.com.fitogether.api.resolver

import br.com.fitogether.api.data.entity.user.UserEntity
import org.springframework.core.MethodParameter
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserIdArgumentResolver : HandlerMethodArgumentResolver {
    // Verifica se o argumento é um Long e está anotado com @AuthenticationPrincipal
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == Long::class.java &&
                parameter.hasParameterAnnotation(AuthenticationPrincipal::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        // Obtém o usuário autenticado
        val principal = SecurityContextHolder.getContext().authentication.principal as? UserEntity
            ?: throw IllegalStateException("Usuário não autenticado")
        // Garante que o ID do usuário esteja inicializado
        return requireNotNull(principal.id) { "ID do usuário não foi inicializado" }
    }
}