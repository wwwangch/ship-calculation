package com.iscas.base.biz.autoconfigure.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

/**
 * 错误页面配置
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/17 8:41
 * @since jdk1.8
 */
@AutoConfiguration
@Slf4j
@ConditionalOnClass(ErrorPageRegistry.class)
public class ErrorPageAutoConfiguration implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        log.info("-----------错误页面路径配置------------");
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
                new ErrorPage(HttpStatus.FORBIDDEN, "/403"),
                new ErrorPage(HttpStatus.BAD_REQUEST, "/400"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
        log.info("-----------错误页面路径配置结束------------");
    }
}
