package med.voll.api.controller.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info().title("API Voll.med")
                        .description("API Rest de la aplicacion Voll.med, que contiene las funcionalidades de CRUD de medicos, pacientes e historias clinicas. ")
                        .contact(new Contact()
                                .name("Equipo Backend")
                                .email("XxL1U@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://voll.med/api/licencia"))
                        .version("2.0.0"));   }


    public void message() {
        System.out.println("bearer is working");
    }
}
