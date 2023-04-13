package com.iscas.base.biz.config.elasticjob.v3;

import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/16 11:24
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class ElasticRegCenterConfig {
    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter regCenter(
            @Value("${elastic-job.zookeeper.server-lists}") final String serverList,
            @Value("${elastic-job.zookeeper.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }
}
