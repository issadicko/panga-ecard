package net.panga.ecard.config

import net.panga.ecard.dao.entity.User
import net.panga.ecard.dao.repository.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.security.crypto.password.PasswordEncoder
import reactor.core.publisher.Mono
import java.util.*

@Configuration
class UserInitializerConfig(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Bean
    fun userInitializer() = ApplicationRunner { _: ApplicationArguments? ->
        val defaultPhoneNumber = "05260006"
        userRepository.findByPhoneNumber(defaultPhoneNumber)
            .switchIfEmpty(
                userRepository.save(
                    User(
                        uuid = UUID.randomUUID(),
                        phoneNumber = defaultPhoneNumber,
                        email = "issa.dicko@panga.net",
                        password = passwordEncoder.encode("123456"),
                        firstname = "Issa Hamadoum",
                        lastname = "DICKO"
                    ))).subscribe()
    }
}