package com.iscas.base.biz.autoconfigure.charset;

import com.iscas.base.biz.filter.CharsetEncodingSetFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/9 16:40
 * @since jdk1.8
 */
@AutoConfiguration
@Slf4j
public class CharsetAutoConfiguration {
    @Value("${server.servlet.encoding.charset:UTF-8}")
    private String charset;

    @Bean
    public CharsetEncodingSetFilter charsetEncodingSetFilter() {
        return new CharsetEncodingSetFilter(charset);
    }

    @SuppressWarnings("unchecked")
    @Bean
    public FilterRegistrationBean charsetFilterRegistrationBean(CharsetEncodingSetFilter charsetEncodingSetFilter) {
        log.info("-----注册编码设置过滤器-------");
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        frb.setFilter(charsetEncodingSetFilter);
        frb.addUrlPatterns("/*");
        frb.setName("charsetSetFilter");
        log.info("-----注册编码设置过滤器结束-------");
        return frb;
    }
}
