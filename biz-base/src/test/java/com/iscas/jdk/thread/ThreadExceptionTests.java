package com.iscas.jdk.thread;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 多线程下异常处理
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/26 16:04
 * @since jdk1.8
 */
public class ThreadExceptionTests {

    @Test
    public void test() {
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
                throw new RuntimeException("error");
           }
       };
       new Thread(runnable).start();

       Runnable runnable1 = () -> {
           try {
               int a = 4 / 0;
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       };

       new Thread(runnable1).start();
       System.out.println(111);
    }
}
