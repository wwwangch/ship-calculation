package com.iscas.jdk.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/7 10:07
 * @since jdk1.8
 */
public class ReadWriteLockTests {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    @Test
    public void test1() {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            lock.writeLock().lock();
            System.out.println(name + ":write is lock" );
            lock.writeLock().unlock();

            lock.readLock().lock();
            System.out.println(name + ":read is lock" );
            lock.readLock().unlock();
        };
        for (int i = 0; i < 2 ; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(runnable);
        }
    }
}
