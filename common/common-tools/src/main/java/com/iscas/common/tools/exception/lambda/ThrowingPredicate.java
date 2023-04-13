package com.iscas.common.tools.exception.lambda;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Lambda表达式能够抛出异常的Predicate
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/17 14:07
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowingPredicate<T>{
    /**
     * test
     * @since jdk11
     * @date 2022/4/18
     * @param t t
     * @return boolean
     * @throws Exception e
     */
    boolean test(T t) throws Exception;

    /**
     * and
     * @since jdk11
     * @date 2022/4/18
     * @param other other
     * @return java.util.function.Predicate<T>
     */
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> {
            try {
                return test(t) && other.test(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * negate
     * @since jdk11
     * @date 2022/4/18
     * @return java.util.function.Predicate<T>
     */
    default Predicate<T> negate() {
        return (t) -> {
            try {
                return !test(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * or
     * @since jdk11
     * @date 2022/4/18
     * @param other other
     * @return java.util.function.Predicate<T>
     */
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> {
            try {
                return test(t) || other.test(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * isEqual
     * @since jdk11
     * @date 2022/4/18
     * @param targetRef targetRef
     * @return java.util.function.Predicate<T>
     */
    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : targetRef::equals;
    }

    /**
     * not
     * @since jdk11
     * @date 2022/4/18
     * @param target target
     * @return java.util.function.Predicate<T>
     */
    @SuppressWarnings("unchecked")
    static <T> Predicate<T> not(Predicate<? super T> target) {
        Objects.requireNonNull(target);
        return (Predicate<T>)target.negate();
    }
}