package cl.duoc.MicroServicioRoles.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class swaggerconfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Microservicio de rol")
            .version("1.0")
            .description("Documentación de la API de rol")
        );
    }
}
