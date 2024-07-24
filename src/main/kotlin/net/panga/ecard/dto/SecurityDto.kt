package net.panga.ecard.dto

interface SecurityDto {

    data class LoginRequest(
        val username: String,
        val password: String
    )


    data class LoginResponse(
        val token: String,
        val userDto: UserDto
    )

    data class UserDto(
        val id: Long,
        val username: String,
        val email: String,
        val password: String,
        val firstname: String,
        val lastname: String,
    )


    data class RegisterRequest(
        val username: String,
        val email: String,
        val password: String
    )
}