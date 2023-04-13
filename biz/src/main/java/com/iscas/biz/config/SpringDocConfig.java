package com.iscas.biz.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class SpringDocConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("newframe-接口文档")
                        .description("基于SpringDoc的在线接口文档")
                        .version("0.0.1"))
                .components(new Components().addSecuritySchemes("tokenHeader", new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER).name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("tokenHeader"));
    }

    @Bean
    public GroupedOpenApi calculationApi() {
        return GroupedOpenApi.builder()
                .group("计算软件")
                .packagesToScan("com.iscas.biz.calculation")
                .build();
    }
/*    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("权限相关")
                .packagesToScan("com.iscas.biz.controller.common.auth")
                .build();
    }*/

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("默认")
                .pathsToMatch("/**")
                .build();
    }



}
