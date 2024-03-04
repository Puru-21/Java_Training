package com.training.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collection;
import java.util.Collections;
@Configuration
@EnableSwagger2
public class Swagger_IMPL {
	@Bean
	public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.training.project"))
                .build()
                .apiInfo(apiInfo());
    }
	private ApiInfo apiInfo() {
        return new ApiInfo(
                "Employee_Databse_Service REST API", //title
                "Springboot rest api developed for training purposes.", //description
                "Version 1.0", //version
                "Terms of service", //terms of service URL
                new Contact("Pulkit Sharma", "portal.accenture.com","mailtopuru652@gmail.com"),
                "License of API", "API license URL", Collections.emptyList()); // contact info
    }
}

