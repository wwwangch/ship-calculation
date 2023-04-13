package com.iscas.base.biz.config.elasticjob.v3;

import org.apache.shardingsphere.elasticjob.api.ElasticJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * elastic-job处理
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/17 14:33
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@ConditionalOnElasticJob
@Component
public class ElasticJobHandler {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private CoordinatorRegistryCenter regCenter;

    /**启动一个定时任务*/
    public void startJob(ElasticJobEntity jobEntity) {
        JobConfiguration jobConfiguration = JobConfiguration.newBuilder(jobEntity.getJobName(), jobEntity.getShardingTotalCount())
                .cron(jobEntity.getCron())
                .description(jobEntity.getDescription())
                .shardingItemParameters(jobEntity.getShardingItemParameters())
                //overwrite必须设置为true，否则修改cron表达式不生效
                .overwrite(true)
                .build();
        new ScheduleJobBootstrap(regCenter, (ElasticJob) context.getBean(jobConfiguration.getJobName()), jobConfiguration).schedule();
    }
}
