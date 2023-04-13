package com.iscas.base.biz.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/11/30 16:30
 * @since jdk1.8
 */
public class CaffCacheUtilsTest {

    @Test
    void set() {
        CaffCacheUtils.set("x", 1234);
        Assertions.assertEquals(1234, CaffCacheUtils.get("x"));
    }

    @Test
    void testSet() throws InterruptedException {
        CaffCacheUtils.set("x", 1234, 5);
        Assertions.assertEquals(1234, CaffCacheUtils.get("x"));
        TimeUnit.SECONDS.sleep(5);
        Assertions.assertNull(CaffCacheUtils.get("x"));
    }

    @Test
    void setnx() throws InterruptedException {
        CaffCacheUtils.set("x", 1234, 5);
        CaffCacheUtils.setnx("x", 5678, 0);
        Assertions.assertEquals(1234, CaffCacheUtils.get("x"));
        TimeUnit.SECONDS.sleep(5);
        CaffCacheUtils.setnx("x", 5678, 0);
        Assertions.assertEquals(5678, CaffCacheUtils.get("x"));
    }

    @Test
    void expire() throws InterruptedException {
        CaffCacheUtils.set("x", 1234);
        Assertions.assertEquals(1234, CaffCacheUtils.get("x"));
        CaffCacheUtils.expire("x", 3);
        TimeUnit.SECONDS.sleep(3);
        Assertions.assertNull(CaffCacheUtils.get("x"));
    }

    @Test
    void cleanup() {
        CaffCacheUtils.set("x", 1234);
        CaffCacheUtils.cleanup();
        Assertions.assertNull(CaffCacheUtils.get("x"));
    }

    @Test
    void get() {
    }

    @Test
    void remove() {
        CaffCacheUtils.set("x", 1234);
        CaffCacheUtils.remove("x");
        Assertions.assertNull(CaffCacheUtils.get("x"));
    }

    @Test
    void compute() {
        CaffCacheUtils.set("x", 1234);
        CaffCacheUtils.compute("x", 365 * 24 * 3600, (k, v) -> 5678);
        Assertions.assertEquals(5678, CaffCacheUtils.get("x"));
    }

    @Test
    void getAll() {
        CaffCacheUtils.set("x", 1234);
        Assertions.assertNotNull(CaffCacheUtils.getAll());
    }

    @Test
    void getAll2() {
        CaffCacheUtils.set("x", 1234);
        Assertions.assertNotNull(CaffCacheUtils.getAll(List.of("x")));
    }


}