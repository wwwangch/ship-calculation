package com.iscas.common.ehcache2.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/24 13:30
 */
class EhCacheUtilsTest {
    @BeforeEach
    public void before() {
        EhCacheUtils.init(EhCacheUtils.class);
    }


    @Test
    public void testCache() {
        EhCacheUtils.put("tokenCache", "key", "value");
        System.out.println(EhCacheUtils.get("tokenCache", "key"));
        EhCacheUtils.remove("tokenCache", "key");
        System.out.println(EhCacheUtils.get("tokenCache", "key"));
    }

}