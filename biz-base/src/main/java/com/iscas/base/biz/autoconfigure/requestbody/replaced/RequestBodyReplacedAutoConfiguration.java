package com.iscas.base.biz.autoconfigure.requestbody.replaced;

import com.iscas.base.biz.filter.ReplaceRequestBodyFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/2/21 16:38
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = "requestbody.replaced.enabled", havingValue = "true")
public class RequestBodyReplacedAutoConfiguration {

    @Bean
    public ReplaceRequestBodyFilter replaceRequestBodyFilter() {
        return new ReplaceRequestBodyFilter();
    }

    @Bean
    public FilterRegistrationBean requestbodyReplacedFilterRegistrationBean() {
        log.info("-----注册替换requestbody过滤器-------");
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(replaceRequestBodyFilter());
        frb.setOrder(Ordered.HIGHEST_PRECEDENCE);
        frb.addUrlPatterns("/*");
        frb.setName("requestbodyReplacedFilter");
        log.info("-----注册替换requestbody过滤器结束-------");
        return frb;

    }
}
