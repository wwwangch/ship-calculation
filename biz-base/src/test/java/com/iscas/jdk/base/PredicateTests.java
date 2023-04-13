package com.iscas.jdk.base;

import java.util.function.Predicate;

/**
 * Predicate 测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/29 9:17
 * @since jdk1.8
 */
public class PredicateTests {
    static class A {
        public static void add (int x,  Predicate<Integer> predicate) {
            if (predicate.test(x)) {
                System.out.println(x * x);
            } else {
                System.out.println("error");
            }
        }
    }

    public static void main(String[] args) {
        A.add(-5, (a) -> {
           return a >0;
        });
    }
}
