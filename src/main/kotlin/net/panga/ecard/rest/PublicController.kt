package net.panga.ecard.rest

import net.panga.ecard.rest.dto.ResponseWrapper
import net.panga.ecard.rest.dto.SecurityDto
import net.panga.ecard.service.contract.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/public")
class PublicController(
        private val userService: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody registrationRequest: SecurityDto.RegistrationRequest): Mono<ResponseWrapper<SecurityDto.UserDto>>{
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
    fun login(@RequestBody loginRequest: SecurityDto.LoginRequest): Mono<ResponseWrapper<SecurityDto.LoginResponse>>{
        return userService.login(loginRequest)
                .map {
                    ResponseWrapper(
                            data = it,
                            message = "User logged in successfully",
                            errorCode = 200,
                            successful = true
                    )
                }
    }

}