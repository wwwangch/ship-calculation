package com.iscas.base.biz.autoconfigure.valid;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.Validator;
import java.util.Properties;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/6 14:01
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Configuration(proxyBeanMethods = false)
public class ValidatorAutoConfiguration {
    @Autowired
    private MessageSource messageSource;

    /**
     * 配置校验框架 快速返回模式
     */
    @Bean
    public Validator validator() {
        @SuppressWarnings("resource") LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        // 设置国际化源
        factoryBean.setValidationMessageSource(messageSource);
        // 设置使用 HibernateValidator 校验器
        factoryBean.setProviderClass(HibernateValidator.class);
        Properties properties = new Properties();
        // 设置 快速异常返回
        properties.setProperty("hibernate.validator.fail_fast", "true");
        factoryBean.setValidationProperties(properties);
        // 加载配置
        factoryBean.afterPropertiesSet();
        return factoryBean.getValidator();
    }
}
