package xyz.ajarindong.api.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun springShopOpenAPI(): OpenAPI? {
        return OpenAPI()
                .components(Components()
                        .addSecuritySchemes("bearer-token",
                                SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(SecurityRequirement().addList("bearer-token"))
                .info(Info().title("AjarinDong")
                        .description("API documentation for AjarinDong")
                        .version("v1.0.0")
                        .contact(Contact().name("I Putu Prema Ananda D.N").email("putu@purema.xyz").url("https://ajarindong.xyz"))
                )
    }
}