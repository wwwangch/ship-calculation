package com.iscas.base.biz.service.common;

import com.iscas.base.biz.service.IAuthCacheService;
import com.iscas.base.biz.util.CacheUtils;
import com.iscas.base.biz.util.CommonRedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/7 22:50
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "AlibabaServiceOrDaoClassShouldEndWithImpl", "AlibabaUndefineMagicConstant", "rawtypes", "unchecked"})
@Service
public class AuthCacheService implements IAuthCacheService {
    private final CacheManager cacheManager;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    private CommonRedisHelper commonRedisHelper;

    @Value("${user.max.sessions:1}")
    private int userMaxSessions;

    private final Map<String, List<String>> jdkList = new HashMap<>();

    public AuthCacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


//    @Override
//    public String createCodeAndPut(String secretKey) {
//        String uuid = UUID.randomUUID().toString();
//        // 放入缓存
//        CacheUtils.putCache(Constants.CAPTCHA_CACHE, uuid, secretKey);
//        return uuid;
//    }

//    @Override
//    public void remove(String key, String cacheKey) {
////        AuthCacheUtils.remove(key, cacheKey);
//        CacheUtils.evictCache(cacheKey, key);
//    }

//    @Override
//    public void set(String key, Object value, String cacheKey) {
//        CacheUtils.putCache(cacheKey, key, value);
//    }

//    @Override
//    public void set(String key, Object value, String cacheKey, int ttl) {
//        AuthCacheUtils.put(key, value, cacheKey, ttl);
//    }

//    @Override
//    public Object get(String key, String cacheKey) {
//        return CacheUtils.getCache(cacheKey, key, Object.class);
//    }

    @Override
    public void rpush(String key, String value, String cacheKey) {
        if (!isRedisCacheManager()) {
            synchronized (key.intern()) {
                List<String> values = jdkList.computeIfAbsent(key, k -> new ArrayList<>());
                if (llen(key, cacheKey) >= userMaxSessions) {
                    //移除较早登录的会话
//                    Optional.ofNullable(lpop(key, cacheKey)).ifPresent(token -> remove(token, cacheKey));
                    Optional.ofNullable(lpop(key, cacheKey)).ifPresent(token -> CacheUtils.evictCache(cacheKey, token));
                }
                //加入新会话
                values.add(value);
            }
        } else {
            try {
                for (int i = 0; i < 300; i++) {
                    //使用分布式锁，防止同时移除，使得刚登录的用户也退出
                    boolean lock = commonRedisHelper.lock(key);
                    if (lock) {
                        if (llen(key, cacheKey) >= userMaxSessions) {
                            //移除较早登录的会话
//                            Optional.ofNullable(lpop(key, cacheKey)).ifPresent(token -> remove(token, cacheKey));
                            Optional.ofNullable(lpop(key, cacheKey)).ifPresent(token -> CacheUtils.evictCache(cacheKey, token));
                        }
                        //加入新会话
                        redisTemplate.opsForList().rightPush(key, value);
                        break;
                    } else {
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                commonRedisHelper.delete(key);
            }
        }
    }

    @Override
    public String lpop(String key, String cacheKey) {
        if (!isRedisCacheManager()) {
            List<String> values = jdkList.computeIfAbsent(key, k -> new ArrayList<>());
            if (values.size() > 0) {
                //移除
                return values.remove(0);
            }
        } else {
            return (String) redisTemplate.opsForList().leftPop(key);
        }
        return null;
    }

    @Override
    public int llen(String key, String cacheKey) {
        if (!isRedisCacheManager()) {
            return Optional.ofNullable(jdkList.get(key)).map(List::size).orElse(0);
        } else {
            return Optional.ofNullable(redisTemplate.opsForList().size(key))
                    .map(Long::intValue).orElse(0);
        }
    }

    @Override
    public boolean listContains(String key, String value) {
        if (!isRedisCacheManager()) {
            return Optional.ofNullable(jdkList.get(key)).map(list -> list.contains(value)).orElse(false);
        } else {
            try {
                Long aLong = redisTemplate.opsForList().indexOf(key, value);
                return aLong >= 0;
            } catch (Exception e) {
                return false;
            }
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isRedisCacheManager() {
        return cacheManager instanceof RedisCacheManager;
    }

    private RedisCacheManager castToRedisCacheManager() {
        return (RedisCacheManager) cacheManager;
    }

}
