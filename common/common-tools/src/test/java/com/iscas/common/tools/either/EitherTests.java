package com.iscas.common.tools.either;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/18
 * @since jdk1.8
 */
public class EitherTests {

    @Test
    public void test() {
        Stream.of(1, 3, 4 ,5, 0,  6)
                .map(Either.lift(i -> 100 / i))
                .forEach(System.out::println);
        //输出
        //Right(100)
        //Right(33)
        //Right(25)
        //Right(20)
        //Left(java.lang.ArithmeticException: / by zero)
        //Right(16)
    }

}
