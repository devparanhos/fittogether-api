package br.com.fitogether.api.config

import br.com.fitogether.api.resolver.UserIdArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val userIdArgumentResolver: UserIdArgumentResolver) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        // Registra o UserIdArgumentResolver
        resolvers.add(userIdArgumentResolver)
    }
}