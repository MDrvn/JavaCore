package com.pizzeria.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Pizzeria API Documentation",
                version = "1.0",
                description = "Документация для API системы управления пиццерией"
        )
)
public class SwaggerConfig {
}