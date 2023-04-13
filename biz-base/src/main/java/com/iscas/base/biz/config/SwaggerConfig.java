//package com.iscas.base.biz.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
///**
// * swagger配置
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/7/17 22:51
// * @since jdk1.8
// */
////@Configuration
////@EnableSwagger2
////@RefreshScope
//public class SwaggerConfig {
//    @Value("${swagger.enable: true}")
//    private boolean swaggerEnable;
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .enable(swaggerEnable)
//                .select()
//                // 自行修改为自己的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.iscas.web"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("controller-api文档")
//                .description("")
//                //服务条款网址
//                //.termsOfServiceUrl("http://blog.csdn.net/forezp")
//                .version("1.0")
//                //.contact(new Contact("帅呆了", "url", "email"))
//                .build();
//    }
//}
