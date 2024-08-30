package net.panga.ecard.rest.dto

data class UserRegistrationDto(
    var phone: String,
    var password: String,
    var email: String,
    var firstName: String,
    var lastName: String,
)

