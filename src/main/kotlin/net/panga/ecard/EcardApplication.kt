package net.panga.ecard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EcardApplication

fun main(args: Array<String>) {
    runApplication<EcardApplication>(*args)
}
