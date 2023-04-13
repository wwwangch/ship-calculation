//package com.iscas.biz.config;
//
//import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
//import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
//import org.springframework.boot.actuate.endpoint.web.*;
//import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.util.StringUtils;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.HttpAuthenticationScheme;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * swagger配置
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/08/28
// * @since jdk1.8
// */
//@SuppressWarnings("unused")
//@AutoConfiguration
//@EnableOpenApi
//@EnableKnife4j
//public class Swagger3Config {
//    @Value("${swagger.enable: true}")
//    private boolean swaggerEnable;
//
//    private final String version = "1.0";
//
//    @Bean
//    public Docket defaultApi() {
//        return new Docket(DocumentationType.OAS_30)
//                .groupName("默认")
//                .apiInfo(defaultApiInfo())
//                .enable(swaggerEnable)
//                .securitySchemes(List.of(tokenScheme()))
//                .securityContexts(List.of(tokenContext()))
////                .globalOperationParameters(setHeaderToken())
//                .select()
//                // 自行修改为自己的包路径
//                // todo 这里不能使用扫描ApiOperation的方式，springboot2.6 后扫描到flowable的东西就会出问题，所以只能用basePackage
//                .apis(RequestHandlerSelectors.basePackage("com.iscas"))
//
////                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build()/*.forCodeGeneration(true)*/;
//
//    }
//
//    @Bean
//    public Docket authApi() {
//
//        return new Docket(DocumentationType.OAS_30)
//                .groupName("权限相关")
//                .apiInfo(new ApiInfoBuilder().title("权限相关-API")
//                        .description("权限相关API")
//                        .version(version).build())
//                .enable(swaggerEnable)
//                .securitySchemes(List.of(tokenScheme()))
//                .securityContexts(List.of(tokenContext()))
////                .globalOperationParameters(setHeaderToken())
//                .select()
//                // 自行修改为自己的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.iscas.biz.controller.common.auth"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo defaultApiInfo() {
//        return new ApiInfoBuilder()
//                .title("newframe-接口文档")
//                .description("基于swagger3的在线接口文档，如不喜欢此风格，可尝试使用http://<IP:PORT>/<context-path>/doc.html")
//                //服务条款网址
//                //.termsOfServiceUrl("http://blog.csdn.net/forezp")
//                .version(version)
//                //.contact(new Contact("帅呆了", "url", "email"))
//                .build();
//    }
//
//    private HttpAuthenticationScheme tokenScheme() {
//        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("Authorization").build();
//    }
//
//    private SecurityContext tokenContext() {
//        return SecurityContext.builder()
//                .securityReferences(List.of(SecurityReference.builder()
//                        .scopes(new AuthorizationScope[0])
//                        .reference("Authorization")
//                        .build()))
//                .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
//                .build();
//    }
//
////    @Bean
////    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
////                                                                         ServletEndpointsSupplier servletEndpointsSupplier,
////                                                                         ControllerEndpointsSupplier controllerEndpointsSupplier,
////                                                                         EndpointMediaTypes endpointMediaTypes,
////                                                                         CorsEndpointProperties corsProperties,
////                                                                         WebEndpointProperties webEndpointProperties,
////                                                                         Environment environment) {
////        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
////        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
////        allEndpoints.addAll(webEndpoints);
////        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
////        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
////        String basePath = webEndpointProperties.getBasePath();
////        EndpointMapping endpointMapping = new EndpointMapping(basePath);
////        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
////        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(),
////                new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping);
////    }
////
////    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
////
////        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) ||
////                ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
////
////    }
//
//}
