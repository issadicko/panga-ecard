package net.panga.ecard.service

import net.panga.ecard.dao.repository.UserRepository
import net.panga.ecard.rest.dto.SecurityDto
import net.panga.ecard.rest.dto.UserRegistrationDto
import net.panga.ecard.service.contract.UserService
import net.panga.ecard.utils.exception.BusinessException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserService {

    override fun register(registrationRequest: UserRegistrationDto): Mono<SecurityDto.UserDto?> {

        registrationRequest.password = passwordEncoder.encode(registrationRequest.password)

        return userRepository.findByPhoneNumber(registrationRequest.phone)
            .flatMap {
                Mono.error<SecurityDto.UserDto>(BusinessException("User you are trying to register already exists"))
            }
            .switchIfEmpty(
                userRepository.save(
                    SecurityDto.UserDto.toEntity(registrationRequest)
                )
                    .map { SecurityDto.UserDto.toDto(it) }
            )

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