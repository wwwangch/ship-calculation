package com.iscas.base.biz.schedule;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务注册类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/2/25 18:15
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Component
public class CronTaskRegister implements DisposableBean {

    private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);
    private final Map<String, Runnable> scheduledTaskIds = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getScheduler() {
        return this.taskScheduler;
    }

    /**
     * 新增定时任务
     *
     * @param task task
     * @param cronExpression cronExpression
     */
    public void addCronTask(String taskId, Runnable task, String cronExpression) {
        addCronTask(taskId, new CronTask(task, cronExpression));
    }

    public void addCronTask(String taskId, CronTask cronTask) {
        if (cronTask != null) {
            Runnable newTask = cronTask.getRunnable();
            if (scheduledTaskIds.containsKey(taskId)){
                //清理旧任务
                Runnable oldTask = scheduledTaskIds.get(taskId);
                removeCronTask(oldTask);
            }
            //记录并调度新任务
            this.scheduledTaskIds.put(taskId,newTask);
            this.scheduledTasks.put(newTask, scheduleCronTask(cronTask));
        }
    }

    /**
     * 移除定时任务
     *
     * @param task task
     */
    public void removeCronTask(Runnable task) {
        ScheduledTask scheduledTask = this.scheduledTasks.remove(task);
        if (scheduledTask != null) {
            scheduledTask.cancel();
        }
    }

    public ScheduledTask scheduleCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());

        return scheduledTask;
    }


    @Override
    public void destroy() {
        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }
        this.scheduledTasks.clear();
    }
}
