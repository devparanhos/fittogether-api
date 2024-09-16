package br.com.fitogether.api.config.security

import br.com.fitogether.api.config.filter.JwtAuthenticationFilter
import br.com.fitogether.api.data.entity.user.UserEntity
import br.com.fitogether.api.data.repository.user.UserRepository

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.proc.SecurityContext import org.springframework.beans.factory.annotation.Value

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

import java.time.Instant

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userRepository: UserRepository
) {

    @Value("\${security.jwt.public-key}")
    private lateinit var publicKey : RSAPublicKey

    @Value("\${security.jwt.private-key}")
    private lateinit var privateKey : RSAPrivateKey

    private val publicPostEndpoints = arrayOf("/users", "users/validate-email", "/users/validate-code", "users/login")

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        http.csrf{ it.disable() }
            .authorizeHttpRequests{ it
                .requestMatchers(HttpMethod.POST, *publicPostEndpoints).permitAll()
                .anyRequest().authenticated()
            }
            .sessionManagement{ it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(
                JwtAuthenticationFilter(
                    jwtDecoder = jwtDecoder(),
                    userRepository = userRepository
                ),
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

    @Bean
    fun jwtEncoder() : JwtEncoder {
        val jwk = RSAKey.Builder(publicKey).privateKey(privateKey).build()
        val jwks = ImmutableJWKSet<SecurityContext>(JWKSet(jwk))

        return NimbusJwtEncoder(jwks)
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(publicKey).build()
    }

    fun generateToken(user: UserEntity) : String{
        val claims = JwtClaimsSet.builder()
            .issuer("fittogther")
            .subject(user.username)
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(3600))
            .build()

        return jwtEncoder().encode(JwtEncoderParameters.from(claims)).tokenValue
    }
}