package com.iscas.base.biz.aop.norepeat.submit;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/27 22:19
 * @since jdk1.8
 */
public interface INoRepeatSubmitRedisHandler {
    /**
     * 检测
     *
     * @param key key
     * @return boolean
     * @date 2022/4/24
     * @since jdk11
     */
    boolean check(String key);

    /**
     * 移除
     *
     * @param key key
     * @date 2022/4/24
     * @since jdk11
     */
    void remove(String key);
}
