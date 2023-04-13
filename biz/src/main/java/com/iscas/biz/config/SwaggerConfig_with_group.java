//package com.iscas.biz.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * swagger配置
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/7/17 22:51
// * @since jdk1.8
// */
//@Configuration
//@EnableSwagger2
//@RefreshScope
//public class SwaggerConfig_with_group {
//    @Value("${swagger.enable: true}")
//    private boolean swaggerEnable;
//    private String version = "1.0";
//    @Bean
//    public Docket ontologyApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("ontology(本体管理)")
//                .apiInfo(new ApiInfoBuilder().title("本体管理-API")
//                        .description("本体管理相关的控制层接口API")
//                        .version(version).build())
//                .enable(swaggerEnable)
//                .select()
//                // 自行修改为自己的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.iscas.platform.ontology.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//
//    @Bean
//    public Docket analysisApi() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("analysis(可视化分析)")
//                .apiInfo(new ApiInfoBuilder().title("可视化分析-API")
//                        .description("可视化分析相关的控制层接口API")
//                        .version(version).build())
//                .enable(swaggerEnable)
//                .select()
//                // 自行修改为自己的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.iscas.platform.analysis.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Bean
//    public Docket dataminingApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("datamining(模型训练)")
//                .apiInfo(new ApiInfoBuilder().title("模型训练-API")
//                        .description("模型训练相关的控制层接口API")
//                        .version(version).build())
//                .enable(swaggerEnable)
//                .select()
//                // 自行修改为自己的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.iscas.platform.datamining.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Bean
//    public Docket etlApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("ETL(ETL)")
//                .apiInfo(new ApiInfoBuilder().title("ETL-API")
//                        .description("ETL相关的控制层接口API")
//                        .version(version).build())
//                .enable(swaggerEnable)
//                .select()
//                // 自行修改为自己的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.iscas.platform.etl.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//}
