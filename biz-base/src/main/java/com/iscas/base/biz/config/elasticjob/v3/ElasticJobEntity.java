package com.iscas.base.biz.config.elasticjob.v3;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * job实体
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/16 9:24
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class ElasticJobEntity {
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务类型 0.SimpleJob 1.DataflowJob 2.ScriptJob
     */
    private Integer type = 0;
    /**
     * cron表达式
     */
    private String cron;
    /**
     * 分片数量
     */
    private int shardingTotalCount = 1;
    /**
     * 当前任务参数
     */
    private String jobParameter;
    /**
     * 当前分片参数
     */
    private String shardingItemParameters;
    /**
     * 任务说明
     */
    private String description;
}
