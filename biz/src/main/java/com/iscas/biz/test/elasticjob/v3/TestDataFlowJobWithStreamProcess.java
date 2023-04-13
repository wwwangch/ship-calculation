package com.iscas.biz.test.elasticjob.v3;

import com.iscas.base.biz.config.elasticjob.v3.ConditionalOnElasticJob;
import com.iscas.common.tools.core.random.RandomStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 测试开启一个流式定时任务,配置为流式拉取，会一直拉取，直到fetchData没有获取到数据或者发生了重新分片才会停止
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/17 13:16
 * @since jdk1.8
 */
@ConditionalOnElasticJob
@Component("testDataFlowJobWithStreamProcess")
@Slf4j
public class TestDataFlowJobWithStreamProcess implements DataflowJob<String> {
    private ThreadLocal<Integer> testCount = new ThreadLocal<>();
    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
                //        System.out.println(String.format("------Thread ID: %s, 任务总片数: %s, " +
//                        "当前分片项: %s,当前参数: %s," +
//                        "当前任务名称: %s,当前任务参数: %s,"+
//                        "当前任务的id: %s"
//                ,
//                //获取当前线程的id
//                Thread.currentThread().getId(),
//                //获取任务总片数
//                shardingContext.getShardingTotalCount(),
//                //获取当前分片项
//                shardingContext.getShardingItem(),
//                //获取当前的参数
//                shardingContext.getShardingParameter(),
//                //获取当前的任务名称
//                shardingContext.getJobName(),
//                //获取当前任务参数
//                shardingContext.getJobParameter(),
//                //获取任务的id
//                shardingContext.getTaskId()
//        ));
        if (testCount.get() == null) {
            testCount.set(0);
            return null;
        }
        testCount.set(testCount.get() + 1);
        log.debug("测试流式定时任务拉取数据，配置为流式，第{}次：", testCount.get());
        if (testCount.get() >= 10) {
            testCount.set(0);
            return null;
        }
        return Arrays.asList(RandomStringUtils.randomStr(5), RandomStringUtils.randomStr(5),
                RandomStringUtils.randomStr(5), RandomStringUtils.randomStr(5));

    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> data) {
        log.debug("测试消费流式数据,共{}次，配置为流式，数据为:{}", testCount.get(), data.toString());
    }
}
