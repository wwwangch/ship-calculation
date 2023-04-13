package com.iscas.biz.test.norepeate.submit;

import com.iscas.base.biz.aop.norepeat.submit.INoRepeatSubmitRedisHandler;
import com.iscas.common.redis.tools.ConfigInfo;
import com.iscas.common.redis.tools.JedisConnection;
import com.iscas.common.redis.tools.RedisInfo;
import com.iscas.common.redis.tools.impl.JedisClient;
import com.iscas.common.redis.tools.impl.standalone.JedisStandAloneConnection;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/29 21:18
 * @since jdk1.8
 */
@Component
public class NoRepeatSubmitRedisHandler implements INoRepeatSubmitRedisHandler {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private JedisClient jedisClient = null;
    private JedisClient getConnection() {
        synchronized (this) {
            if (jedisClient == null) {
                synchronized (this) {
                    if (jedisClient == null) {
                        JedisConnection jedisConnection = new JedisStandAloneConnection();
                        ConfigInfo configInfo = new ConfigInfo();
                        configInfo.setMaxIdle(10);
                        configInfo.setMaxTotal(50);
                        configInfo.setMaxWait(20000);
                        RedisInfo redisInfo = new RedisInfo();
                        redisInfo.setHost("localhost");
                        redisInfo.setPort(6379);
//        redisInfo.setPwd("iscas");
                        redisInfo.setTimeout(10000);
                        configInfo.setRedisInfos(Arrays.asList(redisInfo));
                        jedisClient = new JedisClient(jedisConnection, configInfo);
                    }

                }

            }
            return jedisClient;
        }

    }

    @Override
    public boolean check(String key) {
        JedisClient connection = getConnection();
        String s = connection.acquireLock(key, 10000);
        if (s == null) {
            return false;
        }
        threadLocal.set(s);
        return true;
    }
    @Override
    public void remove(String key) {
        String s = threadLocal.get();
        if (s != null) {
            JedisClient connection = getConnection();
            connection.releaseLock(key, s);
        }
    }

}
