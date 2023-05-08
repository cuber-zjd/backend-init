package com.cuber.backend.system.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    @Bean("docketBean")
    public Docket docketBean(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("# Knife4j RESTful APIs")
                        .termsOfServiceUrl("http://doc.xiaominfo.com")
                        .contact(new Contact("cuber","www.cuber.com","cuber@123.com"))
                        .version("1.0")
                        .build())
                .groupName("用户服务")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cuber.backend.system.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder().securityReferences(Collections.singletonList(new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "")}))).forPaths(PathSelectors.regex("^(?!auth).*$")).build());
    }

    private List<? extends SecurityScheme> securitySchemes() {
        // 安全模式，这里指定token通过Authorization请求头传递
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", In.HEADER.toValue());

        return Collections.singletonList(apiKey);

    }

}
