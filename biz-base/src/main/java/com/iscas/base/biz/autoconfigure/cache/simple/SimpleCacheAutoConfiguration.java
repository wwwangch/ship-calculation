package com.iscas.base.biz.autoconfigure.cache.simple;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import com.iscas.base.biz.autoconfigure.cache.CacheSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/22 9:05
 */
@AutoConfiguration
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "simple")
@EnableConfigurationProperties(CacheSpec.class)
public class SimpleCacheAutoConfiguration {

    @Autowired
    private CacheSpec cacheSpec;

    @Bean
    public CacheManager cacheManager(Ticker ticker) {
        SimpleCacheManager manager = new SimpleCacheManager();
        if (cacheSpec != null) {
            List<CaffeineCache> caches = cacheSpec.getSpecs().entrySet().stream()
                    .map(entry -> buildCache(entry.getKey(), entry.getValue(), ticker))
                    .toList();
            manager.setCaches(caches);
        }
        return manager;
    }

    private CaffeineCache buildCache(String name, CacheSpec.Spec spec, Ticker ticker) {
        final Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
                .expireAfterWrite(spec.getExpireTime().getSeconds(), TimeUnit.SECONDS)
                .maximumSize(spec.getMaxSize())
                .ticker(ticker);
        return new CustomizedCaffeineCache(name, caffeineBuilder.build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }
}
