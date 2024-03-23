package com.example.tripy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(servers = {
    @Server(url = "/", description = "Default Server URL")
})
@EnableScheduling
@SpringBootApplication
@EnableJpaAuditing
public class TripyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripyApplication.class, args);
    }

}
