package com.greatlearning.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket employeeAPI()

    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("Employee API").select().apis(RequestHandlerSelectors.basePackage("com.greatlearning.ems.controller"))
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Employee API").description("Contract for CRUD operations")
                .termsOfServiceUrl("open").contact(new Contact("Lib API","url","emailId"))
                .license("EMS License").licenseUrl("fake.url").version("1.0").build();
    }
}



