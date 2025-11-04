package com.br.pdvpostocombustivel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.br.pdvpostocombustivel")
@OpenAPIDefinition(
    info = @Info(
        title = "PDV Posto Combustível API",
        version = "1.0",
        description = "API para gerenciamento de PDV de posto de combustível",
        contact = @Contact(
            name = "Suporte",
            email = "suporte@pdvposto.com"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Local Development Server")
    }
)
public class PdvpostocombustivelApplication {
    public static void main(String[] args) {
        SpringApplication.run(PdvpostocombustivelApplication.class, args);
    }
}