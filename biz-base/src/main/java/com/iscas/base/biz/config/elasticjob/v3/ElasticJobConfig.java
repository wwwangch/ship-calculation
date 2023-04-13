package com.iscas.base.biz.config.elasticjob.v3;

import org.apache.shardingsphere.elasticjob.api.ElasticJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * elasticjob配置
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/16 11:17
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class ElasticJobConfig {
    @Autowired
    private CoordinatorRegistryCenter regCenter;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ElasticJobEntityConfig jobEntityConfig;

    @PostConstruct
    public void executor() {
        List<JobConfiguration> list = getJobConfiguration();
        JobConfiguration jobConfiguration;
        list.forEach(job -> new ScheduleJobBootstrap(regCenter, (ElasticJob) applicationContext.getBean(job.getJobName()), job).schedule());
    }

    public List<JobConfiguration> getJobConfiguration() {
        List<ElasticJobEntity> jobEntityList = jobEntityConfig.getJobs();
        List<JobConfiguration> list = new ArrayList<>(jobEntityList.size());
        jobEntityList.forEach(jobEntity -> list.add(JobConfiguration.newBuilder(jobEntity.getJobName(), jobEntity.getShardingTotalCount())
                .cron(jobEntity.getCron())
                .description(jobEntity.getDescription())
                .shardingItemParameters(jobEntity.getShardingItemParameters())
                //overwrite必须设置为true，否则修改cron表达式不生效
                .overwrite(true)
                .build()));
        return list;
    }

}
