package com.iscas.base.biz.autoconfigure.cache.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/22 14:17
 */
public class CustomizedRedisCache extends RedisCache {
    private static final String[] WILD_CARD = new String[]{"*", "?", "[", "]"};
    private RedisConnectionFactory connectionFactory;

    /**
     * Create new {@link RedisCache}.
     *
     * @param name        must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     */
    protected CustomizedRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig,
                                   RedisConnectionFactory connectionFactory) {
        super(name, cacheWriter, cacheConfig);
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void evict(Object key) {
        if (key instanceof String keyStr && StringUtils.containsAny(keyStr, WILD_CARD)) {
            // 如果是key是字符串类型，且以*结尾，以通配符方式删除缓存
            String cacheKey = super.createCacheKey(key);
            //用scan替代keys
            RedisConnection connection = null;
            try {
                connection = connectionFactory.getConnection();
                ScanOptions scanOptions = ScanOptions.scanOptions().match(cacheKey).count(Integer.MAX_VALUE).build();
                Cursor<byte[]> cursor = connection.scan(scanOptions);
                while (cursor.hasNext()) {
                    connection.del(cursor.next());
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } else {
            super.evict(key);
        }
    }
}
