package com.iscas.common.jrejson.tools;

import com.iscas.common.redis.tools.ConfigInfo;
import com.iscas.common.redis.tools.JedisConnection;
import com.iscas.common.redis.tools.RedisInfo;
import com.iscas.common.redis.tools.impl.standalone.JedisStandAloneConnection;
import com.redislabs.modules.rejson.JReJSON;
import com.redislabs.modules.rejson.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/29 9:31
 * @since jdk1.8
 */
public class JreJsonUtilsTests {
    private JReJSON client = null;
    @BeforeEach
    public void before() {
        JedisConnection jedisConnection = new JedisStandAloneConnection();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setMaxIdle(10);
        configInfo.setMaxTotal(50);
        configInfo.setMaxWait(20000);
        RedisInfo redisInfo = new RedisInfo();
        redisInfo.setHost("172.16.10.160");
        redisInfo.setPort(31158);
//        redisInfo.setPwd("iscas");
        redisInfo.setTimeout(10000);
        configInfo.setRedisInfos(Arrays.asList(redisInfo));
        client = JreJsonUtils.initJreJsonClient(jedisConnection, configInfo);
    }

    /**
     * 测试连接
     * */
    @Test
    public void testConnection() {
        System.out.println(client);
        Assertions.assertNotNull(client);
    }

    /**
     * 测试设置值
     * */
    @Test
    public void testSet() {
        client.set("key1", "中国");
        Assertions.assertEquals("中国", client.get("key1"));
    }

    /**
     * 测试path
     * */
    @Test
    public void testPath() {
        String val = client.get("key1", String.class, new Path("."));
        System.out.println(val);
        Assertions.assertEquals("中国", val);

        String val2 = client.get("key1", String.class, Path.ROOT_PATH);
        System.out.println(val2);
        Assertions.assertEquals("中国", val2);
    }

    /**
     * 测试path2
     * */
    @Test
    public void testPath2() {
        Map<String, String> map = Map.of("a", "我", "b", "网关");
        client.set("key2", map, Path.ROOT_PATH);
        System.out.println(client.get("key2", Map.class));
        System.out.println(client.get("key2", Map.class, Path.ROOT_PATH));
        System.out.println(client.get("key2", String.class, new Path(".a")));
        System.out.println(client.get("key2", String.class, new Path(".b")));

        //设置值
        client.set("key2", "啦啦啦", new Path(".b"));
        System.out.println(client.get("key2", String.class, new Path(".b")));

        //设置新值
        client.set("key2", "哈哈哈", new Path(".c"));
        System.out.println(client.get("key2", String.class, new Path(".c")));
        System.out.println(client.get("key2", Map.class, new Path(".")));

        //设置一个集合值
        client.set("key2", List.of(1, 2, 4, 5, 6), new Path(".d"));
        System.out.println(client.get("key2", Map.class, Path.ROOT_PATH));
        System.out.println(client.get("key2", List.class, new Path(".d")));
        System.out.println(client.get("key2", Integer.class, new Path(".d[0]")));

        //向集合追加值
        client.arrAppend("key2", new Path(".d"),  7, 8, 9);
        System.out.println(client.get("key2", List.class, new Path(".d")));

        //向集合插入值
        client.arrInsert("key2", new Path(".d"), 2L, 100);
        System.out.println(client.get("key2", List.class, new Path(".d")));

        //删除一个值
        client.del("key2", new Path(".d[2]"));
        System.out.println(client.get("key2", List.class, new Path(".d")));

        //删除一个key
        client.set("key3", "xxxx", Path.ROOT_PATH);
        System.out.println(client.get("key3", String.class, Path.ROOT_PATH));
        client.del("key3");
        System.out.println(client.get("key3", String.class, Path.ROOT_PATH));

        //查询集合索引
        Long index = client.arrIndex("key2", new Path(".d"), 1);
        System.out.println(index);

        //获取集合长度
        Long len = client.arrLen("key2", new Path(".d"));
        System.out.println(len);

        //从集合中pop出一个数
        Integer popVal = client.arrPop("key2", Integer.class, new Path(".d"));
        System.out.println(popVal);
        System.out.println(client.get("key2", List.class, new Path(".d")));

        Integer popVal2 = client.arrPop("key2", Integer.class, new Path(".d"), 0L);
        System.out.println(popVal2);
        System.out.println(client.get("key2", List.class, new Path(".d")));

        //保留集合中start-end的数据，其他的删除
        Long num = client.arrTrim("key2", new Path(".d"), 0L, 3L);
        System.out.println(num);
        System.out.println(client.get("key2", List.class, new Path(".d")));

        //获取多个值
        client.set("key3", List.of("java", "golang"));
        List<Object> mgetRes = client.mget(Object.class, "key2", "key3");
        System.out.println(mgetRes);

        Class<?> key2Class = client.type("key2");
        System.out.println(key2Class);

        Class<?> key2Class2 = client.type("key2", new Path(".d"));
        System.out.println(key2Class2);
    }

}
