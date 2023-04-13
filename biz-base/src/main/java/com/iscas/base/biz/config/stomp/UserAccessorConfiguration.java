package com.iscas.base.biz.config.stomp;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/7 15:09
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@AutoConfiguration
public class UserAccessorConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UserAccessor getUser(){
        return new DefaultUserAccessor();
    }
}
