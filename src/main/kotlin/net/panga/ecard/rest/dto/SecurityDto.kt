package net.panga.ecard.rest.dto

import com.fasterxml.jackson.annotation.JsonInclude
import net.panga.ecard.dao.entity.User

interface SecurityDto {

    data class AuthenticationRequest(
        val username: String,
        val password: String
    )

    data class AuthenticationResponse(
        val token: String,
        val userDto: UserDto
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
        }
    }


    data class RegistrationRequest(
        val phoneNumber: String,
        val email: String,
        val password: String
    )
}