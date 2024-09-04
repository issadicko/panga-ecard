import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.servers.Server
import io.swagger.v3.oas.models.info.Info

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(Info()
                .title("PANGA - ECARD BACKEND APIS")
                .version("1.0")
                .description("Panga ecards backend apis"))
            .addServersItem(Server().url("http://localhost:8080"))
            .addServersItem(Server().url("http://37.60.233.97:9190"))
            .addServersItem(Server().url("https://ecard-backend.dickode.net"))
    }
}