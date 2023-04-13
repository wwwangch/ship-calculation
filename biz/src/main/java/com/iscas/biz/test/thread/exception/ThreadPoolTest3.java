package com.iscas.biz.test.thread.exception;

import com.iscas.templet.exception.Exceptions;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/14 17:54
 * @since jdk11
 */
public class ThreadPoolTest3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTest3.class);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 3000, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("customFactory-");
                thread.setUncaughtExceptionHandler(new CustomExceptionHandler());
                return thread;
            }
        }, new ThreadPoolExecutor.AbortPolicy());
        AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();
        executor.execute(() -> {
            threadAtomicReference.set(Thread.currentThread());
            System.out.println("线程内部：" + threadAtomicReference.get().getState());
            System.out.println(4 / 0);
        });
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            throw Exceptions.runtimeException(e);
        }
        System.out.println("线程外部：" + threadAtomicReference.get().getState());

    }

    public static class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            LOGGER.error("线程执行异常", e);
        }
    }
}
