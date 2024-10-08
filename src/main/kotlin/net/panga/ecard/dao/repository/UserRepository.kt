package net.panga.ecard.dao.repository

import net.panga.ecard.dao.entity.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<User, Long> {
    fun findByPhoneNumber(phoneNumber: String): Mono<User?>
}