package com.iscas.jdk.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/7 9:37
 * @since jdk1.8
 */
public class ThreadLocalRandomTests {
    @Test
    public void test1() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int i = random.nextInt();
        System.out.println(i);
        System.out.println(random.nextInt(1,2));
    }
    @Test
    public void test2() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        Runnable r = () -> {
            int i = random.nextInt(1, 10000);
            System.out.println(i);
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 50 ; i++) {
            executorService.submit(r);
        }
    }

    @Test
    public void test3() {
        Runnable r = () -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int i = random.nextInt(1, 10000);
            System.out.println(i);
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 50 ; i++) {
            executorService.submit(r);
        }
    }
}
