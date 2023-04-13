package com.iscas.base.biz.config.ratelimiter;

import com.iscas.base.biz.config.StaticInfo;
import com.iscas.base.biz.filter.RateLimiterFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/18 11:30
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@Slf4j
@EnableConfigurationProperties(RateLimiterProps.class)
public class RateLimiterConfig {
    @Autowired
    private RateLimiterProps rateLimiterProps;

    public RateLimiterFilter rateLimiterFilter(){
        return new RateLimiterFilter(rateLimiterProps);
    }
    @Bean
    public FilterRegistrationBean rateFilterRegistrationBean(){
        log.info("-----注册rateLimiter过滤器-------");
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
        frb.setFilter(rateLimiterFilter());
        frb.addUrlPatterns("/*");
        frb.setName("ratelimiter");

        //将开启限流标记为true，在方法级限流时使用
        StaticInfo.ENABLE_RATELIMITER = true;
        log.info("-----注册rateLimiter过滤器结束-------");
        return frb;
    }
}
