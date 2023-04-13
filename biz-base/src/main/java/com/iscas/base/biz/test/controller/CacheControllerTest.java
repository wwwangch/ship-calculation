package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.util.CacheUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/22 9:25
 */
@RestController
@RequestMapping("/test/cache")
public class CacheControllerTest {

    /**
     * 测试缓存失效
     * */
    @GetMapping("/t1")
    @Cacheable(value = "test", key = "'aaa'")
    public ResponseEntity t1() {
        return new ResponseEntity("test" + new Date());
    }

    /**
     * 使用cacheManager测试缓存失效
     * */
    @GetMapping("/t2")
    public ResponseEntity t2() throws InterruptedException {
        CacheManager cacheManager = SpringUtils.getBean(CacheManager.class);
        Cache testCache = cacheManager.getCache("test");
        testCache.put("xxx", "yyy");
        System.out.println(testCache.get("xxx").get());
        TimeUnit.SECONDS.sleep(11);
        System.out.println(testCache.get("xxx"));
        testCache.put("xxx", "zzz");
        System.out.println(testCache.get("xxx").get());
        testCache.evict("xxx");
        System.out.println(testCache.get("xxx"));
        Cache authCache = cacheManager.getCache("auth");
        authCache.put("xxx", "mmm");
        System.out.println(authCache.get("xxx").get());
        TimeUnit.SECONDS.sleep(11);
        System.out.println(authCache.get("xxx").get());
        return new ResponseEntity();
    }

    /**
     * 测试使用不在配置文件中的缓存名称
     * */
    @GetMapping("/t3")
    public ResponseEntity t3() throws InterruptedException {
        CacheManager cacheManager = SpringUtils.getBean(CacheManager.class);
        Cache noneCache = cacheManager.getCache("none");
        System.out.println(noneCache);
        return new ResponseEntity();
    }

    /**
     * 测试使用不在配置文件中的缓存名称
     * */
    @GetMapping("/t4")
    @Cacheable(value = "none", key = "'xxx'")
    public ResponseEntity t4() throws InterruptedException {
        return new ResponseEntity("test" + new Date());
    }

    /**
     * 测试放入复杂数据类型
     * */
    @GetMapping("/t5")
    public ResponseEntity t5() throws InterruptedException {
        CacheUtils.putCache("auth", "a", new ResponseEntity<>());
        Object xxx = CacheUtils.getCache("auth", "a", Object.class);
        ResponseEntity res = CacheUtils.getCache("auth", "a", ResponseEntity.class);
//        String res2 = CacheUtils.getCache("auth", "a", String.class);

        CacheUtils.putCache("auth", new ResponseEntity<>(), new ResponseEntity<>());

        ResponseEntity res3 = CacheUtils.getCache("auth", new ResponseEntity<>("xxx"), ResponseEntity.class);
        ResponseEntity res4 = CacheUtils.getCache("auth", new ResponseEntity<>(), ResponseEntity.class);

        CacheUtils.putCache("auth", new ResponseEntity<>(), 12);
        Integer count = CacheUtils.getCache("auth", new ResponseEntity<>(), Integer.class);

        return new ResponseEntity("test" + new Date());
    }

    /**
     * 测试使用通配符删除缓存
     * */
    @GetMapping("/t6")
    public ResponseEntity t6() throws InterruptedException {
        CacheUtils.putCache("auth", "a:1", new ResponseEntity<>());
        CacheUtils.putCache("auth", "a:2", new ResponseEntity<>());
        System.out.println(CacheUtils.getCache("auth", "a:2", ResponseEntity.class));
        CacheUtils.evictCache("auth", "a:*");
        System.out.println(CacheUtils.getCache("auth", "a:2", ResponseEntity.class));
        return new ResponseEntity("test" + new Date());
    }

}
