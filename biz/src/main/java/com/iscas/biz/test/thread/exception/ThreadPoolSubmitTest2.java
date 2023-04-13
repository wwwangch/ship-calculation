package com.iscas.biz.test.thread.exception;

import com.iscas.templet.exception.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/14 17:54
 * @since jdk11
 */
public class ThreadPoolSubmitTest2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolSubmitTest2.class);

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 3000, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();
        executor.submit(() -> {
            threadAtomicReference.set(Thread.currentThread());
            try {
                System.out.println("线程内部：" + threadAtomicReference.get().getState());
                System.out.println(4 / 0);
            } catch (Exception e) {
                LOGGER.error("执行异常", e);
            }
        });
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            throw Exceptions.runtimeException(e);
        }
        System.out.println("线程外部：" + threadAtomicReference.get().getState());

    }

}
