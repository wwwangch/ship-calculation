package com.iscas.common.tools.exception.lambda;

/**
 * Lambda表达式能够抛出异常的function
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/17 14:07
 * @since jdk1.8
 */
@FunctionalInterface
public interface ThrowingFunction<T, R> {
    /**
     * apply
     * @since jdk11
     * @date 2022/4/18
     * @param t t
     * @return R
     * @throws Exception e
     */
    R apply(T t) throws Exception;
}