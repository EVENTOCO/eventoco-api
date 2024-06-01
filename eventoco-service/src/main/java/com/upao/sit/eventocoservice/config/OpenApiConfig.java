package com.upao.sit.eventocoservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//http://localhost:8080/api/v1/swagger-ui/index.html
//https://www.bezkoder.com/spring-boot-swagger-3/
@Configuration
public class OpenApiConfig {


    @Value("${eventoco.openapi.dev-url}")
    private String devUrl;

    @Value("${eventoco.openapi.test-url}")
    private String testUrl;

    @Value("${eventoco.openapi.dev-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        // Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server testServer = new Server();
        testServer.setUrl(testUrl);
        testServer.setDescription("Server URL in Test environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        // Información de contacto
        Contact contact = new Contact();
        contact.setEmail("eventoco.0105@gmail.com");
        contact.setName("eventoco");//nombre starturp
        contact.setUrl("https://eventoco.netlify.app");//URL del landing page

        // Licencia
        License mitLicense = new License().name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        // Información general de la API
        Info info = new Info()
                .title("EventoCo API")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints.")
                .termsOfService("https://www.eventoco.com/terms")
                .license(mitLicense);


        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, testServer, prodServer));
    }
}
