package com.iscas.base.biz.autoconfigure.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/28 9:58
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@AutoConfiguration
@EnableConfigurationProperties(value = TokenProps.class)
public class TokenAutoConfiguration {
    @Autowired
    private TokenProps tokenProps;
}
