package net.panga.ecard.service.contract

import net.panga.ecard.rest.dto.SecurityDto
import net.panga.ecard.rest.dto.UserRegistrationDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {
    fun register(registrationRequest: UserRegistrationDto): Mono<SecurityDto.UserDto?>
    fun login(loginRequest: SecurityDto.LoginRequest): Mono<SecurityDto.AuthenticationResponse>
    fun getUserById(uuid: String): Mono<SecurityDto.UserDto>
    fun getUserByUsername(username: String): Mono<SecurityDto.UserDto>
    fun getAllUsers(page: Int, size: Int): Flux<List<SecurityDto.UserDto>>
}