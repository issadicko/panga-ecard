package net.panga.ecard.security

import net.panga.ecard.dao.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import reactor.core.publisher.Mono

@Configuration
class ApplicationSecurity(
    private val userRepository: UserRepository
) {

    @Bean
    fun userDetailsService(): ReactiveUserDetailsService {
        return ReactiveUserDetailsService { username ->
            userRepository.findByPhoneNumber(username)
                .switchIfEmpty(Mono.error(UsernameNotFoundException("User not found")))
                .mapNotNull { user -> user } // Map User to UserDetails
        }
    }

}