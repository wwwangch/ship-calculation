package com.iscas.biz.mp.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * quartz任务
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/26 10:11
 * @since jdk1.8
 */
@Data
public class QrtzJob {

    /**
     * id
     * */
    private Integer id;

    /**
     * 任务名字
     * */
    private String jobName;

    /**
     * cron表达式
     * */
    private String cron;

    /**
     * 描述
     * */
    private String description;

    /**
     * 任务的bean的class名（包名+类型）
     * */
    private String beanClass;

    /**
     * 状态
     * */
    private String status;

    /**
     * 任务分组
     * */
    private String jobGroup;

    /**
     * 任务参数
     * */
    private String jobDataMap;

    /**
     * 上次执行时间
     * */
    private LocalDateTime lastFireTime;

    /**
     * 上次出发时间
     * */
    private LocalDateTime nextFireTime;

    /**
     * 创建时间
     * */
    private LocalDateTime createTime;

    /**
     * 创建人
     * */
    private String createBy;

    /**
     * 修改时间
     * */
    private LocalDateTime updateTime;

    /**
     * 修改人
     * */
    private String updateBy;

}
