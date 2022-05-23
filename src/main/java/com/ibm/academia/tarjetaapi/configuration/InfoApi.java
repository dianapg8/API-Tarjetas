package com.ibm.academia.tarjetaapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfoApi {
    @Bean
    public OpenAPI tarjetasOpenApi(){
        OpenAPI openAPI = new OpenAPI();

        openAPI.info(new Info().title("Recomendación de tarjetas-API Documentation")
                .version("v.1.0.0")
                .description("A traves de esta API podras consultar la tarjeta de credito" +
                        " mas adecuada para un perfil de cliente.").license(new License().name("Tarjeta Application")
                        .url("https://github.com/JSPRM/ProyectoAcademiaIBM"))
                .contact(new Contact().name("Diana P, Lizbeth R, Pedro R y Jose S.").email("rm.pedro@ibm.com")));

        return openAPI;


    }
}
