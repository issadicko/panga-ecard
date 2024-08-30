package net.panga.ecard.rest.dto

import com.fasterxml.jackson.annotation.JsonInclude

interface SecurityDto {

    data class LoginRequest(
        val username: String,
        val password: String
    )


    data class LoginResponse(
        val token: String,
        val userDto: UserDto
    )

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class UserDto(
        val id: Long,
        val username: String,
        val email: String,
        val password: String,
        val firstname: String,
        val lastname: String,
    )


    data class RegistrationRequest(
        val phoneNumber: String,
        val email: String,
        val password: String
    )
}