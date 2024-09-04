package net.panga.ecard.rest

import net.panga.ecard.dao.entity.Ecard
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/v1/ecards")
class EcardController {

    @GetMapping("/my-cards")
    fun myCards(): Flux<Ecard> {
        return Flux.empty()
    }

}