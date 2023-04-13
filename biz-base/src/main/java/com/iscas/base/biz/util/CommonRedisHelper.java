package com.iscas.base.biz.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
 
/**
 * Redis帮助类
 * @author zhuquanwen
 */
@SuppressWarnings({"rawtypes", "unchecked", "ConstantConditions"})
@Component
@ConditionalOnBean(RedisTemplate.class)
public class CommonRedisHelper {
 
    public static final String LOCK_PREFIX = "redis_lock";
    /**ms*/
    public static final int LOCK_EXPIRE = 3000;
 
    @Autowired
    RedisTemplate redisTemplate;
 
   
    /**
     *  最终加强分布式锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key){
        String lock = LOCK_PREFIX + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
 
            long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
 
 
            if (acquire) {
                return true;
            } else {
 
                byte[] value = connection.get(lock.getBytes());
 
                if (Objects.nonNull(value) && value.length > 0) {
 
                    long expireTime = Long.parseLong(new String(value));
 
                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }
 
    /**
     * 删除锁
     *
     * @param key key
     */
    @SuppressWarnings("unused")
    public void delete(String key) {
        String lock = LOCK_PREFIX + key;
        redisTemplate.execute((RedisCallback) connection -> {
            long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            connection.del(lock.getBytes());
            return null;
        });
    }
 
}