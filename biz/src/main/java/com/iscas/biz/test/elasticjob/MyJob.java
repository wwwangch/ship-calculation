//package com.iscas.biz.test.elasticjob;
//
//import com.dangdang.ddframe.job.api.ShardingContext;
//import com.dangdang.ddframe.job.api.simple.SimpleJob;
//import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
//import org.springframework.stereotype.Component;
//
//
//import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
//
//@ElasticSimpleJob(cron = "0/2 * * * * ?",
//        jobName = "firstJob",
//        shardingTotalCount = 2,
//        jobParameter = "测试参数",
//        shardingItemParameters = "0=A,1=B")
//@Component
//public class MyJob implements SimpleJob {
//
//    @Override
//    public void execute(ShardingContext shardingContext) {
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
//    }
//}