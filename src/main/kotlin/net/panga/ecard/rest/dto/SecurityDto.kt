package net.panga.ecard.rest.dto

import com.fasterxml.jackson.annotation.JsonInclude
import net.panga.ecard.dao.entity.User
import java.util.*

interface SecurityDto {

    data class AuthenticationRequest(
        val username: String,
        val password: String
    )

    data class AuthenticationResponse(
        val token: String,
        val user: UserDto
    )

    data class LoginRequest(
        val username: String,
        val password: String
    )

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class UserDto(
        val id: Long? = null,
        val uuid: String,
        val username: String? = null,
        val email: String? = null,
        val password: String? = null,
        val firstname: String? = null,
        val lastname: String? = null,
    ) {
        companion object {
            fun toDto(user: User): UserDto {
                return UserDto(
                    uuid = user.uuid.toString(),
                    username = user.username,
                    email = user.email,
                    firstname = user.firstname,
                    lastname = user.lastname
                )
            }

            fun toEntity(registrationRequest: UserRegistrationDto): User {
                return User(
                    uuid        = UUID.randomUUID(),
                    phoneNumber = registrationRequest.phone,
                    email       = registrationRequest.email,
                    password    = registrationRequest.password,
                    firstname   = registrationRequest.firstName,
                    lastname    = registrationRequest.lastName
                )
            }
        }
    }
}