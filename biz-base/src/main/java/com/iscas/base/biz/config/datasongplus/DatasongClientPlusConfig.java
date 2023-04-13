package com.iscas.base.biz.config.datasongplus;

import com.iscas.datasong.client.plus.config.MyBeanDefinitionRegistryPostProcessor;
import com.iscas.datasong.client.plus.model.DetProps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/12 14:58
 * @since jdk1.8
 */
@SuppressWarnings({"AccessStaticViaInstance", "AliAccessStaticViaInstance", "AlibabaRemoveCommentedCode", "unused", "UnusedAssignment", "InstantiationOfUtilityClass", "CommentedOutCode"})
@Slf4j
@Lazy(value = false)
@EnableConfigurationProperties(DatasongPlusProps.class)
@ConditionalOnProperty(prefix = "datasong.client.plus",matchIfMissing = true,value = "enabled")
public class DatasongClientPlusConfig implements EnvironmentAware {
    private Environment environment;

//    @Value("${Global.DbServer}")
//    private String dbServer;
//    @Value("${Global.DbName}")
//    private String dbName;
//    @Value("${datasong.client.plus.repository.packages}")
//    private String[] packages;
//    @Autowired
//    private DatasongPlusProps datasongPlusProps;

    //todo 旧的配置方式
//    @Bean
//    public DetBeanPostProcessor detBeanPostProcessor(){
//        log.info("------------配置datasong-client-plus----------------");
//
//        String dbName = this.dbName;
//        String ip = "172.16.10.180";
//        String port = "15680";
//        String s = StringUtils.substringAfter(dbServer, "http://");
//        ip = StringUtils.substringBefore(s, ":");
//        port = StringUtils.substringAfter(s, ":");
//
//        DetProps detProps = new DetProps();
//        detProps.setDbName(dbName);
//        detProps.setIp(ip);
//        detProps.setPort(port);
//        detProps.setProxyType(DetProps.ProxyType.CGLIB);
//        detProps.setBasePackages(datasongPlusProps.getPackages());
//        log.info("------------配置datasong-client-plus结束----------------");
//        return new DetBeanPostProcessor(detProps);
//    }


    @Bean
    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor(){
        //新的配置方式，将repository直接注册到spring
        log.info("------------配置datasong-client-plus----------------");

        String dbName = environment.getProperty("datasong.client.plus.dbname");
        String dbServer = environment.getProperty("datasong.client.plus.dbserver");
        String packages = environment.getProperty("datasong.client.plus.repository.packages");
        String ip = "172.16.10.180";
        String port = "15680";
        String s = StringUtils.substringAfter(dbServer, "http://");
        ip = StringUtils.substringBefore(s, ":");
        port = StringUtils.substringAfter(s, ":");

        DetProps detProps = new DetProps();
        detProps.setDbName(dbName);
        detProps.setIp(ip);
        detProps.setPort(port);
        detProps.setProxyType(DetProps.ProxyType.CGLIB);
        assert packages != null;
        detProps.setRepositoryPackages(packages.split(","));
        log.info("------------配置datasong-client-plus结束----------------");
        return new MyBeanDefinitionRegistryPostProcessor(detProps);
    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        this.environment = environment;
    }
}
