package com.iscas.common.jrejson.tools;

import com.iscas.common.redis.tools.ConfigInfo;
import com.iscas.common.redis.tools.JedisConnection;
import com.iscas.common.redis.tools.impl.standalone.JedisStandAloneConnection;
import com.redislabs.modules.rejson.JReJSON;
import redis.clients.jedis.JedisPool;

/**
 * ReJson工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/29 9:14
 * @since jdk1.8
 */
public class JreJsonUtils {
    private JreJsonUtils() {}

    private volatile static JReJSON client = null;

    public static JReJSON initJreJsonClient(JedisConnection jedisConnection, ConfigInfo configInfo) {
        if (client == null) {
            synchronized (JreJsonUtils.class) {
                if (client == null) {
                    if (!(jedisConnection instanceof JedisStandAloneConnection)) {
                        throw new UnsupportedOperationException("当前只支持JedisStandAloneConnection类型");
                    }
                    jedisConnection.initConfig(configInfo);
                    JedisPool pool = (JedisPool) jedisConnection.getPool();
                    client = new JReJSON(pool);
                }
            }
        }
        return client;
    }

}
