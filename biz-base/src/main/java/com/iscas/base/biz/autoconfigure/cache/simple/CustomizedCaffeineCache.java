package com.iscas.base.biz.autoconfigure.cache.simple;

import com.github.benmanes.caffeine.cache.Cache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.caffeine.CaffeineCache;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/22 15:17
 */
public class CustomizedCaffeineCache extends CaffeineCache {
    private static final String[] WILD_CARD = new String[]{"*", "?", "[", "]"};

    private Cache<Object, Object> cache;

    public CustomizedCaffeineCache(String name, Cache<Object, Object> cache) {
        super(name, cache);
        this.cache = cache;
    }

    public CustomizedCaffeineCache(String name, Cache<Object, Object> cache, boolean allowNullValues) {
        super(name, cache, allowNullValues);
        this.cache = cache;
    }

    @Override
    public void evict(Object key) {
        if (key instanceof String keyStr && StringUtils.containsAny(keyStr, WILD_CARD)) {
            // 将key转为正则表达式
            String pattern = keyStr.replace("*", ".+")
                    .replace("?", ".");
            cache.asMap().keySet().stream().filter(k -> k instanceof String kStr && kStr.matches(pattern))
                    .forEach(super::evict);
        } else {
            super.evict(key);
        }
    }
}
