package com.iscas.biz.test.elasticjob.v3;

import com.iscas.base.biz.config.elasticjob.v3.ConditionalOnElasticJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/16 15:49
 * @since jdk1.8
 */
@ConditionalOnElasticJob
@Component("testSimpleJob")
@Slf4j
public class TestSimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.debug(String.format("------Thread ID: %s, 任务总片数: %s, " +
                        "当前分片项: %s,当前参数: %s," +
                        "当前任务名称: %s,当前任务参数: %s," +
                        "当前任务的id: %s"
                ,
                //获取当前线程的id
                Thread.currentThread().getId(),
                //获取任务总片数
                shardingContext.getShardingTotalCount(),
                //获取当前分片项
                shardingContext.getShardingItem(),
                //获取当前的参数
                shardingContext.getShardingParameter(),
                //获取当前的任务名称
                shardingContext.getJobName(),
                //获取当前任务参数
                shardingContext.getJobParameter(),
                //获取任务的id
                shardingContext.getTaskId()
        ));
    }
}
