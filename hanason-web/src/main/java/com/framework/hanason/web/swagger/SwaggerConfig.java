package com.framework.hanason.web.swagger;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

/**
 * @author sorata
 * @date 2019-11-14 08:58
 */
@Configuration
@EnableSwagger2
@ConditionalOnClass(value = Swagger2DocumentationConfiguration.class)
@Profile({"dev","test"})
public class SwaggerConfig {


    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().description("文档接口").build())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).build();
    }

}
