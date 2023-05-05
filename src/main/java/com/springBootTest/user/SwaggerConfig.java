package com.springBootTest.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI baseOpenAPI(){
        return new OpenAPI().info(
            new Info().title("User Service API")
                      .description("User Service API opearations")
                      .contact(
                        new Contact().name("Tamer Al Moghraby")
                            .email("tamermoghraby@gmail.com")
                      )
        );
    }

}
