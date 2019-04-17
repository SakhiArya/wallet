package com.agro.wallet.config;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.base.url:localhost}")
    @Getter
    private String swaggerBaseUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors
                .basePackage("com.agro.wallet.controller"))
            .paths(PathSelectors.any())
            .build()
            .host(getSwaggerBaseUrl() + ":9921")
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo())
            .consumes(contentTypes())
            .produces(contentTypes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("wallet Apis")
            .description("Apis for wallet")
            .version("1.0")
            .build();
    }

    private Set<String> contentTypes() {
        Set<String> contentTypes = new HashSet<String>();
        contentTypes.add("application/json");
        contentTypes.add("application/xml");
        return contentTypes;
    }
}
