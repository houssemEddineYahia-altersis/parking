package com.test.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String PACKAGE_REST = "com.test.parking";
    @Bean
    public Docket serviceProviderApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("parking-api-1.0").select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_REST))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Parking API")
                .description("Parking api documentation")
                .version("1.0.0")
                .build();
    }
}