package com.iscas.base.biz.config.shedlock;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * ShedLock配置
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/20 19:50
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "DanglingJavadoc", "CommentedOutCode"})
@Lazy(false)
@EnableSchedulerLock(defaultLockAtMostFor = "PT30H")
public class ShedLockConfig {

    /**
     * 使用数据库锁
     * */
    @Bean
    public LockProvider lockProvider(ShedLockDatasourceCreator shedLockDatasourceCreator) {
        return new JdbcTemplateLockProvider(shedLockDatasourceCreator.createDataSource());
    }

    /**使用zookeeper锁*/
//    @Bean
//    public LockProvider lockProvider(org.apache.curator.framework.CuratorFramework client) {
//        return new ZookeeperCuratorLockProvider(client);
//    }

    /**
     * 使用jedis锁
     * */
//    @Bean
//    public LockProvider lockProvider(JedisPool jedisPool) {
//        return new JedisLockProvider(jedisPool);
//    }

    /**
     * 使用springdataredis锁
     * */
//    @Bean
//    public LockProvider lockProvider(RedisTemplate redisTemplate) {
//        return new RedisLockProvider(redisTemplate.getConnectionFactory());
//    }

    /**
     * 使用mongo锁
     * */
//    @Bean
//    public LockProvider lockProvider(MongoClient mongo) {
//        return new MongoLockProvider(mongo, "databaseName");
//    }


}
