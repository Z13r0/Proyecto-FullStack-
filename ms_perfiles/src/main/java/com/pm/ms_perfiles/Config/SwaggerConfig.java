package com.pm.ms_perfiles.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio de Perfiles")
                        .version("1.0.0")
                        .description("API REST para la gestión de perfiles de usuarios en la Plataforma Multimedia")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("tuemail@duocuc.cl")
                                .url("https://github.com/tuusuario")));
    }
}
