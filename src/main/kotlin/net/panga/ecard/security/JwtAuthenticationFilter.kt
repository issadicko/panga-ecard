package net.panga.ecard.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationFilter(
    private val jwtService: ReactiveJwtService,
    private val userDetailsService: ReactiveUserDetailsService
) : WebFilter {

    val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)
    private val permittedEndpoints = listOf(
        "/public",      // Add your public endpoints here
        "/webjars",
        "/swagger-ui",
        "/v3/api-docs"
    )

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {

        val path = exchange.request.uri.path

        log.info("Calling on path {}", path)

        if (permittedEndpoints.any { path.startsWith(it) }) {
            return chain.filter(exchange)
        }

        val authHeader = exchange.request.headers["Authorization"]?.firstOrNull()
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return this.sendUnauthorizedResponse(exchange)
        }

        val jwt = authHeader.substring(7)
        return jwtService.extractUsername(jwt)
            .flatMap { username ->
                userDetailsService.findByUsername(username)
                    .flatMap { userDetails ->
                        jwtService.isTokenValid(jwt, userDetails)
                            .filter { it }
                            .map { UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities) }
                    }
            }
            .flatMap { authentication ->
                chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
            }
    }

    private fun sendUnauthorizedResponse(exchange: ServerWebExchange): Mono<Void> {
        val response = exchange.response
        response.statusCode = HttpStatus.UNAUTHORIZED
        response.headers.contentType = MediaType.APPLICATION_JSON

        val errorMessage = mapOf("error" to "Unauthorized", "message" to "Authentication required")
        val responseBody = jacksonObjectMapper().writeValueAsString(errorMessage)

        val buffer = response.bufferFactory().wrap(responseBody.toByteArray())
        return response.writeWith(Mono.just(buffer))
    }
}
