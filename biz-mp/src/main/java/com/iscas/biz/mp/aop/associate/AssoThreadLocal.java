package com.iscas.biz.mp.aop.associate;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/4 14:53
 * @since jdk1.8
 */
@Deprecated
public class AssoThreadLocal {
    private AssoThreadLocal() {

    }
    @SuppressWarnings("AlibabaThreadLocalShouldRemove")
    public static final ThreadLocal<CustomAssociates> ASSOCIATES_THREAD_LOCAL = new ThreadLocal<>();
}
