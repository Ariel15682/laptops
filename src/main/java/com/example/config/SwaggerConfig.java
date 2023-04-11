package com.example.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

/**
 * Configuracion Swagger para la generacion de documentacion de la API REST
 * HTML http://localhost:8080/swagger-ui/#/
 * JSON http://localhost:8080/v2/api-docs
 */
@Configuration
@EnableSwagger2
//@ConfigurationProperties("app.api")
//@ConditionalOnProperty(name="app.api.swagger.enable", havingValue = "true", matchIfMissing = false)
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiDetails())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
            //.directModelSubstitute(LocalDate.class, java.sql.Date.class)
            //.directModelSubstitute(LocalDateTime.class, java.util.Date.class);
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
             "Spring Boot Laptop API REST", //.title("title")
                  "Laptops commerce API REST", //.description("description")
                  "1.0", //.version("1.0")
                  "http://localhost:8080/api/",
                   new Contact("Ariel", "https://localhost.com", "api@ariel.com"), //.contact
                  "MIT",
             "http://localhost:8080/api/",
                   Collections.EMPTY_LIST // Collections.emptyList()
        );
    }

}


