package com.example.tripy.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    String jwtSchemeName = "JWT TOKEN";
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .addSecurityItem(securityRequirement())
                .components(components());
    }

    private Info apiInfo() {
        return new Info()
                .title("Tripy Swagger")
                .description("트리피 Swagger 입니다!")
                .version("1.0.0");
    }
    private Components components(){
        return new Components()
            .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                .name(jwtSchemeName)
                .type(SecurityScheme.Type.HTTP) // HTTP 방식
                .scheme("bearer")
                .bearerFormat("JWT"));
    }

    private SecurityRequirement securityRequirement(){
        return new SecurityRequirement().addList(jwtSchemeName);
    }
}