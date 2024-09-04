package net.panga.ecard.rest

import net.panga.ecard.dao.entity.User
import net.panga.ecard.rest.dto.ResponseWrapper
import net.panga.ecard.rest.dto.SecurityDto
import net.panga.ecard.rest.dto.UserRegistrationDto
import net.panga.ecard.security.ReactiveJwtService
import net.panga.ecard.service.contract.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/public")
class PublicController(
    private val userService: UserService,
    private val authenticationManager: ReactiveAuthenticationManager,
    private val jwtService: ReactiveJwtService,
    private val userDetailsService: ReactiveUserDetailsService
) {

    @PostMapping("/register")
    fun register(@RequestBody registrationRequest: UserRegistrationDto): Mono<ResponseWrapper<SecurityDto.UserDto>>{
        return userService.register(registrationRequest)
                .map {
                    ResponseWrapper(
                            data = it,
                            message = "User registered successfully",
                            errorCode = 200,
                            successful = true
                    )
                }
    }


    @PostMapping("/login")
    fun authenticate(@RequestBody request: SecurityDto.AuthenticationRequest): Mono<SecurityDto.AuthenticationResponse> {
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )
            .flatMap { userDetailsService.findByUsername(request.username) }
            .flatMap { userDetails ->
                jwtService.generateToken(userDetails)
                    .map {

                        val user = userDetails as User

                        SecurityDto.AuthenticationResponse(
                            token = it,
                            user = SecurityDto.UserDto.toDto(user)
                        )

                    }
            }
            .onErrorResume { Mono.error(ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials")) }
    }

}