package com.iscas.common.ehcache.tools;

import org.ehcache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class EhCacheUtilsTest {
    @BeforeEach
    public void before() {
        EhCacheUtils.init(EhCacheUtils.class);
    }

    @Test
    public void init() {
        EhCacheUtils.init(EhCacheUtils.class);
    }


    @Test
    public void testInit1() {
        EhCacheUtils.init(EhCacheUtils.class, "ehcache.xml");
    }

    @Test
    public void testInit2() throws MalformedURLException {
        EhCacheUtils.init(new File("D:\\coding\\ideaProjects\\newframe\\special\\common-ehcache-tools\\src\\main\\resources\\ehcache.xml"));
    }

    @Test
    public void getCache() {
        Cache<String, String> myCache3 = EhCacheUtils.getCache("myCache3", String.class, String.class);
        System.out.println(myCache3);
    }

    @Test
    public void testCache() {
        EhCacheUtils.put("myCache3", "key", "value");
        System.out.println(EhCacheUtils.get("myCache3", "key", String.class));
        EhCacheUtils.remove("myCache3", "key", String.class);
        System.out.println(EhCacheUtils.get("myCache3", "key", String.class));

    }

    @Test
    public void testCache3() {
        EhCacheUtils.put("myCache3", "key", "value");
        System.out.println(EhCacheUtils.get("myCache3", "key", String.class));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        EhCacheUtils.put("myCache3", "key2", "value2");
        System.out.println(EhCacheUtils.get("myCache3", "key2", String.class));

        while (true) {
            System.out.println(EhCacheUtils.get("myCache3", "key", String.class));

            System.out.println(EhCacheUtils.get("myCache3", "key2", String.class));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}