//package com.iscas.biz.mp.config.db;
//
//import com.alibaba.druid.support.http.WebStatFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//
///**
// * Druid监控
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/8/31 16:07
// * @since jdk1.8
// */
//@SuppressWarnings({"rawtypes", "unused", "unchecked"})
//public class DruidMonitorConfiguration {
//    /**
//     * 升级到springboot3.x以后由于javaee版本变化，Druid暂时没有跟着升级，所有不可用了
//     * 注册一个StatViewServlet
//     * @return ServletRegistrationBean
//     */
//    @SuppressWarnings("AlibabaRemoveCommentedCode")
//    @Bean
//    @Deprecated
//    public ServletRegistrationBean druidStatViewServle2(){
//
////        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
////        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CustomStatViewServlet(),"/druid2/*");
////        //添加初始化参数：initParams
////        //白名单：
//////        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
////        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
////        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
////        //登录查看信息的账号密码.
//////        servletRegistrationBean.addInitParameter("loginUsername","druid");
//////        servletRegistrationBean.addInitParameter("loginPassword","druid");
////        //是否能够重置数据.
////        servletRegistrationBean.addInitParameter("resetEnable","false");
////
////        return servletRegistrationBean;
//        return null;
//
//    }
//
//    /**
//     * 升级到springboot3.x以后由于javaee版本变化，Druid暂时没有跟着升级，所有不可用了
//     * 注册一个：filterRegistrationBean
//     * @return FilterRegistrationBean
//     */
//    @Bean
//    @Deprecated
//    public FilterRegistrationBean druidStatFilter2(){
////        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
////        //添加过滤规则.
////        filterRegistrationBean.addUrlPatterns("/*");
////        //添加不需要忽略的格式信息.
////        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*," +
////                "/swagger-resources/*,/loginTest/*,/api/*,/webjars/*,/webSocketServer/*,/webSocketTest/*,*.html,*.json");
////        return filterRegistrationBean;
//        return null;
//    }
//}
