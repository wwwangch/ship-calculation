package com.iscas.biz.mp.service.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.iscas.biz.mp.aop.enable.ConditionalOnQuartz;
import com.iscas.biz.mp.model.QrtzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务处理器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/14 10:04
 * @since jdk11
 */
@SuppressWarnings("unused")
@Slf4j
@Component
@ConditionalOnQuartz
@DependsOn("applicationUtils")
public class QuartzHandler {

    @Autowired
    private Scheduler scheduler;

    /**
     * 新增定时任务
     *
     * @param job 定义任务
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void start(QrtzJob job) throws SchedulerException, ClassNotFoundException {
        try {
            Class clazz = Class.forName(job.getBeanClass());
            String jobName = job.getJobName();
            String jobGroup = job.getJobGroup();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == cronTrigger) {
                // 处理参数
                Map<String, String> map = new HashMap<>(5);
                String jobDataMap = job.getJobDataMap();
                if (StrUtil.isNotBlank(jobDataMap)) {
                    if (JSONUtil.isJson(jobDataMap)) {
                        Map parseMap = JSONUtil.toBean(jobDataMap, Map.class);
                        parseMap.forEach((k, v) -> map.put(String.valueOf(k), String.valueOf(v)));
                    }
                }
                // 启动定时任务
                JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobName, jobGroup)
                        .setJobData(new JobDataMap(map)).build();
                cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
                        .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).build();
                scheduler.scheduleJob(jobDetail, cronTrigger);
                if (!scheduler.isShutdown()) {
                    scheduler.start();
                }
            } else {
                // 重启定时任务
                cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).build();
                scheduler.rescheduleJob(triggerKey, cronTrigger);
            }
        } catch (SchedulerException | ClassNotFoundException e) {
            log.info("新增定时任务异常：{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 暂停定时任务
     *
     * @param job 定时任务
     * @throws SchedulerException 异常
     */
    public void pasue(QrtzJob job) throws SchedulerException {
        try {
            String jobName = job.getJobName();
            String jobGroup = job.getJobGroup();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            JobKey jobKey = trigger.getJobKey();
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.info("暂停定时任务异常：{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 重启定时任务
     *
     * @param job 定时任务
     * @throws SchedulerException 异常
     */
    public void restart(QrtzJob job) throws SchedulerException {
        try {
            String jobName = job.getJobName();
            String jobGroup = job.getJobGroup();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.info("重启定时任务异常：{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 立即执行一次
     *
     * @param job 定时任务
     * @throws SchedulerException 调度异常
     */
    public void trigger(QrtzJob job) throws SchedulerException {
        boolean result = true;
        try {
            String jobName = job.getJobName();
            String jobGroup = job.getJobGroup();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            JobKey jobKey = trigger.getJobKey();
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.info("立即执行一次异常：{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 修改触发时间表达式
     *
     * @param job 定时任务
     * @throws SchedulerException 调度异常
     */
    public void updateCronExpression(QrtzJob job) throws SchedulerException {
        try {
            String jobName = job.getJobName();
            String jobGroup = job.getJobGroup();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder)
                    .build();
            scheduler.rescheduleJob(triggerKey, cronTrigger);
        } catch (SchedulerException e) {
            log.info("修改触发时间表达式异常：{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 删除定时任务
     *
     * @param job 定时任务
     * @throws SchedulerException 调度异常
     */
    public void delete(QrtzJob job) throws SchedulerException {
        boolean result = true;
        try {
            String jobName = job.getJobName();
            String jobGroup = job.getJobGroup();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            JobKey jobKey = trigger.getJobKey();
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.info("删除定时任务异常：{}", e.getMessage());
            throw e;
        }
    }

    /***
     * 判断是否存在定时任务
     *
     * @param job 定时任务
     * @throws SchedulerException 调度异常
     */
    public boolean has(QrtzJob job) throws SchedulerException {
        boolean result = false;
        try {
            if (!scheduler.isShutdown()) {
                String jobName = job.getJobName();
                String jobGroup = job.getJobGroup();
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
                Trigger trigger = scheduler.getTrigger(triggerKey);
                result = trigger != null;
            }
            return result;
        } catch (SchedulerException e) {
            log.info("判断是否存在定时任务异常：{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 获得定时任务状态
     *
     * @param job 定时任务
     * @throws SchedulerException 调度异常
     */
    public String getStatus(QrtzJob job) throws SchedulerException {
        String status;
        try {
            String jobName = job.getJobName();
            String jobGroup = job.getJobGroup();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            status = triggerState.toString();
        } catch (SchedulerException e) {
            log.info("获得定时任务状态异常：{}", e.getMessage());
            throw e;
        }
        return StrUtil.isNotEmpty(status) ? status : TriggerState.NONE.toString();
    }

    /**
     * 启动调度器
     *
     * @throws SchedulerException 调度异常
     */
    public void startScheduler() throws SchedulerException {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.info("启动调度器异常：{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 关闭调度器
     *
     * @throws SchedulerException 调度异常
     */
    public void standbyScheduler() throws SchedulerException {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.standby();
            }
        } catch (SchedulerException e) {
            log.info("关闭调度器异常：{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 判断调度器是否为开启状态
     *
     * @throws SchedulerException 调度异常
     */
    public boolean isStarted() throws SchedulerException {
        boolean result;
        try {
            result = scheduler.isStarted();
        } catch (SchedulerException e) {
            log.info("判断调度器是否为开启状态异常：{}", e.getMessage());
            throw e;
        }
        return result;
    }

    /**
     * 判断调度器是否为关闭状态
     *
     * @throws SchedulerException 调度异常
     */
    public boolean isShutdown() throws SchedulerException {
        boolean result;
        try {
            result = scheduler.isShutdown();
        } catch (SchedulerException e) {
            log.info("判断调度器是否为关闭状态异常：{}", e.getMessage());
            throw e;
        }
        return result;
    }

    /**
     * 判断调度器是否为待机状态
     *
     * @throws SchedulerException 调度异常
     */
    public boolean isInStandbyMode() throws SchedulerException {
        boolean result;
        try {
            result = scheduler.isInStandbyMode();
        } catch (SchedulerException e) {
            log.info("判断调度器是否为待机状态异常：{}", e.getMessage());
            throw e;
        }
        return result;
    }

    /**
     * 获得下一次执行时间
     *
     * @param cronExpression cron表达式
     * @throws ParseException 时间转换异常
     */
    public LocalDateTime getNextFireTime(String cronExpression) throws ParseException {
        LocalDateTime localDateTime = null;
        try {
            if (StrUtil.isNotEmpty(cronExpression)) {
                CronExpression ce = new CronExpression(cronExpression);
                Date nextInvalidTimeAfter = ce.getNextInvalidTimeAfter(new Date());
                localDateTime = Instant.ofEpochMilli(nextInvalidTimeAfter.getTime()).atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
            }
        } catch (ParseException e) {
            log.info("获得下一次执行时间异常：{}", e.getMessage());
            throw e;
        }
        return localDateTime;
    }

}