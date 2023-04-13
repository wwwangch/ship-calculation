package com.iscas.common.tools.exception;

import com.iscas.common.tools.exception.lambda.Lambdas;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/17 14:19
 * @since jdk1.8
 */
public class LambdaExceptionUtilsTests {
    private List<String> list = Arrays.asList("1", "2", "0", "4", "8x");

    @Test
    public void test0() {
        try {
            list.stream().forEach(i -> System.out.println((100 / Integer.parseInt(i))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void test1() {
//        list.stream().forEach(LambdaExceptionUtils.lambdaRuntimeWrapper(i -> System.out.println((100 / Integer.parseInt(i)))));
//    }

    @Test
    public void test2() {
        try {
            list.stream().forEach(i -> {
                File file = new File(i);
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {
        try {
            list.stream().forEach(Lambdas.wrappeConsumer(i -> {
                File file = new File(i);
                FileInputStream fileInputStream = new FileInputStream(file);
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test31() {
        list.stream().forEach(Lambdas.wrappeConsumer(i -> {
            File file = new File(i);
            FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(fileInputStream);
        }));
    }

    @Test
    public void test4() {
        try {
            list.stream().map(Lambdas.wrapperFunction(i -> "lalala" + Integer.parseInt(i)))
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        try {
            list.stream().filter(Lambdas.wrapperPredicate(i -> i.length() == 1))
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
