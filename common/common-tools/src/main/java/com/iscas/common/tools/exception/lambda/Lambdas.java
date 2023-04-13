package com.iscas.common.tools.exception.lambda;

import java.util.function.*;

/**
 * Lambda表达式异常处理通用工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/17 14:07
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class Lambdas {


    /**
     * lambda consumer统一异常处理
     *
     * @param consumer consumer
     * @return java.util.function.Consumer<T>
     * @date 2021/2/17
     * @since jdk1.8
     */
    public static <T> Consumer<T> wrappeConsumer(ThrowingConsumer<T> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * lambda BiConsumer统一异常处理
     *
     * @param consumer consumer
     * @return java.util.function.Consumer<T>
     * @date 2021/2/17
     * @since jdk1.8
     */
    public static <T, U> BiConsumer<T, U> wrapperBiConsumer(ThrowingBiConsumer<T, U> consumer) {
        return (i, j) -> {
            try {
                consumer.accept(i, j);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * lambda function统一异常处理
     *
     * @param function function
     * @return java.util.function.Consumer<T>
     * @date 2021/2/17
     * @since jdk1.8
     */
    public static <T, R> Function<T, R> wrapperFunction(ThrowingFunction<T, R> function) {
        return i -> {
            try {
                return function.apply(i);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * lambda supplier统一异常处理
     *
     * @param supplier supplier
     * @return java.util.function.Consumer<T>
     * @date 2021/2/17
     * @since jdk1.8
     */
    public static <T> Supplier<T> wrapperSupplier(ThrowingSupplier<T> supplier) {
        return () -> {
            try {
                return (T) supplier.get();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * lambda predicate统一异常处理
     *
     * @param predicate predicate
     * @return java.util.function.Consumer<T>
     * @date 2021/2/17
     * @since jdk1.8
     */
    public static <T> Predicate<T> wrapperPredicate(ThrowingPredicate<T> predicate) {
        return i -> {
            try {
                return predicate.test(i);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}
