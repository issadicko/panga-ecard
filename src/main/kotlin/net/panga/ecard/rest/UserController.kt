package net.panga.ecard.rest

import net.panga.ecard.rest.dto.SecurityDto
import net.panga.ecard.service.contract.UserService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Flux<List<SecurityDto.UserDto>> {
        return userService.getAllUsers(page, size)
    }


    @GetMapping("/{uuid}")
    fun getUserById(@PathVariable uuid: String): Mono<SecurityDto.UserDto> {
        return userService.getUserById(uuid)
    }

    @GetMapping("/username/{username}")
    fun getUserByUsername(@PathVariable username: String): Mono<SecurityDto.UserDto> {
        return userService.getUserByUsername(username)
    }

    @GetMapping("/connected")
    fun getConnectedUser(): Mono<SecurityDto.UserDto> {
        return userService.getConnectedUser()
            .map {
                SecurityDto.UserDto.toDto(it)
            }
    }
}