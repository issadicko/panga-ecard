package net.panga.ecard.service

import net.panga.ecard.dao.repository.UserRepository
import net.panga.ecard.rest.dto.SecurityDto
import net.panga.ecard.service.contract.UserService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    override fun register(registrationRequest: SecurityDto.RegistrationRequest): Mono<SecurityDto.UserDto?> {
        TODO("Not yet implemented")
    }

    override fun login(loginRequest: SecurityDto.LoginRequest): Mono<SecurityDto.AuthenticationResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserById(uuid: String): Mono<SecurityDto.UserDto> {
        TODO("Not yet implemented")
    }

    override fun getUserByUsername(username: String): Mono<SecurityDto.UserDto> {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(page: Int, size: Int): Flux<List<SecurityDto.UserDto>> {
        TODO("Not yet implemented")
    }


}