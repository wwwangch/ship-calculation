package com.iscas.biz.test.thread.exception;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/14 17:54
 * @since jdk11
 */
public class ThreadTest {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(5 / 0);
            System.out.println(11111);
        };
        try {
            new Thread(runnable).start();
        } catch (Exception e) {
            System.out.println("外部抓取异常");
            e.printStackTrace();
        }
    }
}
