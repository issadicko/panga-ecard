package net.panga.ecard.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthenticationFilter
) {

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .cors { it.disable() }
            .authorizeExchange { exchanges ->
                exchanges
                    .pathMatchers(
                        "/api/auth/**",
                        "/public/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                    )
                    .permitAll()
                    .anyExchange()
                    .authenticated()
            }
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .addFilterBefore(jwtAuthFilter, SecurityWebFiltersOrder.AUTHORIZATION)
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .build()
    }


    @Bean
    fun reactiveAuthenticationManager(userDetailsService: ReactiveUserDetailsService, passwordEncoder: PasswordEncoder): ReactiveAuthenticationManager {
        val authenticationManager = UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService)
        authenticationManager.setPasswordEncoder(passwordEncoder)
        return authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}