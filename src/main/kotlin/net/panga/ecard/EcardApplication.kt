package net.panga.ecard

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(
    servers = [
        io.swagger.v3.oas.annotations.servers.Server(
            url = "http://localhost:8080"
        ),
        io.swagger.v3.oas.annotations.servers.Server(
            url = "https://ecard-backend.dickode.net"
        )
    ],
    info = io.swagger.v3.oas.annotations.info.Info(
        title = "Ecard API",
        version = "1.0",
        description = "Ecard API Documentation"
    )
)
class EcardApplication

fun main(args: Array<String>) {
    runApplication<EcardApplication>(*args)

    println()
    println(">>>> Ecard Application Started <<<<")
    println()
}
