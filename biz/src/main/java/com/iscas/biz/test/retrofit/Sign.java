//package com.iscas.biz.test.retrofit;
//
//import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
//import com.github.lianjiatech.retrofit.spring.boot.interceptor.InterceptMark;
//
//import java.lang.annotation.*;
//
///**
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/8/9 13:20
// * @since jdk1.8
// */
//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
//@Documented
//@InterceptMark //必须加这个注解
//public @interface Sign {
//    /**
//     * 密钥key
//     * 支持占位符形式配置。
//     *
//     * @return
//     */
//    String accessKeyId();
//
//    /**
//     * 密钥
//     * 支持占位符形式配置。
//     *
//     * @return
//     */
//    String accessKeySecret();
//
//    /**
//     * 拦截器匹配路径
//     *
//     * @return
//     */
//    String[] include() default {"/**"};
//
//    /**
//     * 拦截器排除匹配，排除指定路径拦截
//     *
//     * @return
//     */
//    String[] exclude() default {};
//
//    /**
//     * 处理该注解的拦截器类
//     * 优先从spring容器获取对应的Bean，如果获取不到，则使用反射创建一个！
//     *
//     * @return
//     */
//    Class<? extends BasePathMatchInterceptor> handler() default SignInterceptor.class;
//}
//
