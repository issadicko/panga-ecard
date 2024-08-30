package net.panga.ecard.rest

import net.panga.ecard.rest.dto.SecurityDto
import net.panga.ecard.service.contract.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
    fun getUserById(uuid: String): Mono<SecurityDto.UserDto> {
        return userService.getUserById(uuid)
    }

    @GetMapping("/username/{username}")
    fun getUserByUsername(username: String): Mono<SecurityDto.UserDto> {
        return userService.getUserByUsername(username)
    }

}