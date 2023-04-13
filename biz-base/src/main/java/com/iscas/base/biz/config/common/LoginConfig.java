package com.iscas.base.biz.config.common;


import com.iscas.base.biz.config.StaticInfo;
import com.iscas.base.biz.filter.LoginFilter;
import com.iscas.base.biz.service.AbstractAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 * @author zhuquanwen
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@Slf4j
public class LoginConfig {
    @Autowired(required = false)
    private AbstractAuthService authService;

    @Bean
    public LoginFilter loginFilter(@Autowired(required = false) AbstractAuthService authService) {
        return new LoginFilter(authService);
    }

    @Bean
    public FilterRegistrationBean loginFilterRegistrationBean() {
        log.info("-----注册登录验证过滤器-------");
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        frb.setFilter(loginFilter(authService));
        frb.addUrlPatterns("/*");
        frb.setName("loginFilter");
        StaticInfo.ENABLE_AUTH = true;
        log.info("-----注册登录验证过滤器结束-------");
        return frb;

    }

}
