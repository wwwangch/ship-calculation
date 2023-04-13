package com.iscas.base.biz.config.xss;

import com.google.common.collect.Maps;
import com.iscas.base.biz.filter.XssFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * xss过滤器配置类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/1/18 14:46
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
@Slf4j
public class XssConfig {
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        log.info("----------------配置XSS过滤器------------------");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        log.info("----------------配置XSS过滤器结束------------------");
        return filterRegistrationBean;
    }

}