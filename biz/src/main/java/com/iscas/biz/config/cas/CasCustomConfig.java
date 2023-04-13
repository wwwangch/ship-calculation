//package com.iscas.biz.config.cas;
//
//import com.iscas.base.biz.config.cas.ConditionalOnCustomCasClient;
//import org.jasig.cas.client.session.SingleSignOutFilter;
//import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
//import org.jasig.cas.client.util.AssertionThreadLocalFilter;
//import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
//import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
//import org.springframework.context.annotation.Bean;
//
///**
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/7/3 17:19
// * @since jdk1.8
// */
//@SuppressWarnings({"unused", "rawtypes", "unchecked"})
//@AutoConfiguration
//@EnableConfigurationProperties(CasProps.class)
//@ConditionalOnCustomCasClient
//public class CasCustomConfig {
//    @Autowired
//    private CasProps casProps;
//
//    private static final boolean CAS_ENABLED = true;
//
//    public CasCustomConfig() {
//    }
//
//
//    @Bean
//    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
//        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<>();
//        listener.setEnabled(CAS_ENABLED);
//        listener.setListener(new SingleSignOutHttpSessionListener());
//        listener.setOrder(1);
//        return listener;
//    }
//
//    /**
//     * 该过滤器用于实现单点登出功能，单点退出配置，一定要放在其他filter之前
//     *
//     * @return FilterRegistrationBean
//     */
//    @Bean
//    public FilterRegistrationBean singleSignOutFilter() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new SingleSignOutFilter());
//        filterRegistration.setEnabled(CAS_ENABLED);
//        if (casProps.getSignOutFilters().size() > 0) {
//            filterRegistration.setUrlPatterns(casProps.getSignOutFilters());
//        } else {
//            filterRegistration.addUrlPatterns("/*");
//        }
//        filterRegistration.addInitParameter("casServerUrlPrefix", casProps.getCasServerUrlPrefix());
//        filterRegistration.setOrder(3);
//        return filterRegistration;
//    }
//
//    /**
//     * 该过滤器负责用户的认证工作
//     *
//     * @return FilterRegistrationBean
//     */
//    @Bean
//    public FilterRegistrationBean authenticationFilter() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new CustomCasAuthenticationFilter(casProps.isRedirectAfterValidation()));
//        filterRegistration.setEnabled(CAS_ENABLED);
//        if (casProps.getAuthFilters().size() > 0) {
//            filterRegistration.setUrlPatterns(casProps.getAuthFilters());
//        } else {
//            filterRegistration.addUrlPatterns("/*");
//        }
//        if (casProps.getIgnoreFilters() != null) {
//            filterRegistration.addInitParameter("ignorePattern", casProps.getIgnoreFilters());
//        }
//        filterRegistration.addInitParameter("casServerLoginUrl", casProps.getCasServerLoginUrl());
//        filterRegistration.addInitParameter("serverName", casProps.getServerName());
//        filterRegistration.addInitParameter("useSession", casProps.isUseSession() ? "true" : "false");
//        filterRegistration.addInitParameter("redirectAfterValidation", casProps.isRedirectAfterValidation() ? "true" : "false");
//        filterRegistration.setOrder(4);
//        return filterRegistration;
//    }
//
//    /**
//     * 该过滤器负责对Ticket的校验工作,使用CAS 3.0协议
//     *
//     * @return FilterRegistrationBean
//     */
//    @Bean
//    public FilterRegistrationBean cas30ProxyReceivingTicketValidationFilter() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
//        filterRegistration.setEnabled(CAS_ENABLED);
//        if (casProps.getValidateFilters().size() > 0) {
//            filterRegistration.setUrlPatterns(casProps.getValidateFilters());
//        } else {
//            filterRegistration.addUrlPatterns("/*");
//        }
//        filterRegistration.addInitParameter("casServerUrlPrefix", casProps.getCasServerUrlPrefix());
//        filterRegistration.addInitParameter("serverName", casProps.getServerName());
//        filterRegistration.setOrder(5);
//        return filterRegistration;
//    }
//
//    @Bean
//    public FilterRegistrationBean httpServletRequestWrapperFilter() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new HttpServletRequestWrapperFilter());
//        filterRegistration.setEnabled(true);
//        if (casProps.getRequestWrapperFilters().size() > 0) {
//            filterRegistration.setUrlPatterns(casProps.getRequestWrapperFilters());
//        } else {
//            filterRegistration.addUrlPatterns("/*");
//        }
//        filterRegistration.setOrder(6);
//        return filterRegistration;
//    }
//
//    /**
//     * 该过滤器使得可以通过org.jasig.cas.client.util.AssertionHolder来获取用户的登录名。
//     * 比如AssertionHolder.getAssertion().getPrincipal().getName()。
//     * 这个类把Assertion信息放在ThreadLocal变量中，这样应用程序不在web层也能够获取到当前登录信息
//     *
//     * @return FilterRegistrationBean
//     */
//    @Bean
//    public FilterRegistrationBean assertionThreadLocalFilter() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new AssertionThreadLocalFilter());
//        filterRegistration.setEnabled(true);
//        if (casProps.getAssertionFilters().size() > 0) {
//            filterRegistration.setUrlPatterns(casProps.getAssertionFilters());
//        } else {
//            filterRegistration.addUrlPatterns("/*");
//        }
//        filterRegistration.setOrder(7);
//        return filterRegistration;
//    }
//}
