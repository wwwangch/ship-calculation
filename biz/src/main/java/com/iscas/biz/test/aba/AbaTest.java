package com.iscas.biz.test.aba;

import com.iscas.templet.exception.Exceptions;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 通过版本号解决CAS-ABA的问题实例
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/26 12:08
 * @since jdk11
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class AbaTest {
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(10, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw Exceptions.runtimeException(e);
            }
            atomicStampedReference.compareAndSet(10, 11, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(11, 10, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "::" + atomicStampedReference.getReference());
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw Exceptions.runtimeException(e);
            }
            while (!atomicStampedReference.compareAndSet(10, 11, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1)) {
                System.out.println("修改失败");
            }
            System.out.println(Thread.currentThread().getName() + "::" + atomicStampedReference.getReference());
        }).start();
    }

}
