package com.iscas.base.biz.config;

/**
 * 一些静态变量
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/18 13:43
 * @since jdk1.8
 */
public class StaticInfo {
    /**是否开启了限流*/
    public static boolean ENABLE_RATELIMITER = false;

    /**是否开启了权限控制*/
    public static boolean ENABLE_AUTH = false;

    /**访问开始时间ThreadLocal*/
    @SuppressWarnings("AlibabaThreadLocalShouldRemove")
    public static ThreadLocal<Long> START_TIME_THREAD_LOCAL = new ThreadLocal<>();
}
