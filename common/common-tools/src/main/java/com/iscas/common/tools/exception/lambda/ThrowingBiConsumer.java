package com.iscas.common.tools.exception.lambda;

/**
 * Lambda表达式能够抛出异常的BiConsumer
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/17 14:07
 * @since jdk1.8
 */
@FunctionalInterface
public interface ThrowingBiConsumer<T, U> {
    /**
     * accept
     * @since jdk11
     * @date 2022/4/18
     * @param t t
     * @param u u
     * @throws Exception 异常
     */
    void accept(T t, U u) throws Exception;
}