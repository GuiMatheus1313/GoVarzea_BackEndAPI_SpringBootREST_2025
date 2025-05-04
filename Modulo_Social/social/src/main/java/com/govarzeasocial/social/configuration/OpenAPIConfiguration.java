package com.govarzeasocial.social.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi(){
        Server server = new Server();
        //TODO Pesquisar sobre o efeito do setURL
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Info information = new Info()
                .title("Módulo Social da API: Pessoa, perfil etc.")
                .version("1.0")
                .description("Mostrando os endpoints do Módulo Social");

        return new OpenAPI().info(information).servers(List.of(server));
    }
}
