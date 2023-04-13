package com.iscas.common.tools.either;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/18 15:20
 * @since jdk1.8
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {

    /**
     * apply
     * @since jdk11
     * @date 2022/4/18
     * @param t t
     * @return R
     * @throws Exception 异常
     */
    R apply(T t) throws Exception;
}