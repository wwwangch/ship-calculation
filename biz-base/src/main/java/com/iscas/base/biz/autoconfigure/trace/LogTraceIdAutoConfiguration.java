package com.iscas.base.biz.autoconfigure.trace;

import com.iscas.base.biz.filter.LogTraceIdFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/12 11:19
 */
@AutoConfiguration
@ConditionalOnClass(LogTraceIdFilter.class)
@Slf4j
public class LogTraceIdAutoConfiguration {
    @Bean
    public LogTraceIdFilter logTraceIdFilter() {
        return new LogTraceIdFilter();
    }

    @SuppressWarnings("unchecked")
    @Bean
    public FilterRegistrationBean traceIdFilterRegistrationBean() {
        log.info("-----注册LogTraceId过滤器-------");
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setOrder(Ordered.HIGHEST_PRECEDENCE);
        frb.setFilter(logTraceIdFilter());
        frb.addUrlPatterns("/*");
        frb.setName("logTraceIdFilter");
        log.info("-----注册LogTraceId过滤器结束-------");
        return frb;
    }
}
