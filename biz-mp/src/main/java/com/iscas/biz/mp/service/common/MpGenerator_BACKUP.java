//package com.iscas.biz.mp.service.common;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
///**
// * mybatis代码生成器service
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/9/26 16:59
// * @since jdk1.8
// */
//@Service
//@ConditionalOnMybatis
//public class MpGenerator_BACKUP {
//    @Value("${spring.datasource.druid.url}")
//    private String dbUrl;
//    @Value("${spring.datasource.druid.username}")
//    private String username;
//    @Value("${spring.datasource.druid.password}")
//    private String password;
//    @Value("${spring.datasource.druid.driver-class-name}")
//    private String driverClassName;
//    @Value("${mp.parent.package.name:com.iscas.biz.tmp}")
//    private String parentPackageName;
//
//    @Value("${mp.parent.path:biz/src/main/java}")
//    private String parentPath;
//
//    public void generator() {
//        GlobalConfig config = new GlobalConfig();
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setDbType(DbType.MYSQL)
//                .setUrl(dbUrl)
//                .setUsername(username)
//                .setPassword(password)
//                .setDriverName(driverClassName);
//        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig
//                .setCapitalMode(true)
//                .setEntityLombokModel(true)
////                .setDbColumnUnderline(true)
//                .setNaming(NamingStrategy.underline_to_camel);
//        config.setActiveRecord(false)
//                .setEnableCache(false)
////                .setAuthor("zhuquanwen")
//                // 这里就直接输出到项目里面，不用再复制进来
//                .setOutputDir(parentPath)
//                .setFileOverride(true)
//                .setServiceName("%sService");
//        new AutoGenerator().setGlobalConfig(config)
//                .setDataSource(dataSourceConfig)
//                .setStrategy(strategyConfig)
//                .setPackageInfo(
//                        new PackageConfig()
//                                .setParent(parentPackageName)
//                                .setController("controller")
//                                .setEntity("model")
//                ).execute();
//    }
//
//}
