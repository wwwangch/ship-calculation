package com.iscas.biz.mp.service.common.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.biz.mp.aop.enable.ConditionalOnQuartz;
import com.iscas.biz.mp.mapper.QrtzJobMapper;
import com.iscas.biz.mp.model.QrtzJob;
import com.iscas.biz.mp.service.common.IQrtzJobService;
import com.iscas.biz.mp.service.common.QuartzHandler;
import com.iscas.common.tools.assertion.AssertObjUtils;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.view.table.TableResponseData;
import com.iscas.templet.view.table.TableSearchRequest;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/26 10:21
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Service
@RequiredArgsConstructor
@ConditionalOnQuartz
public class QrtzJobServiceImpl extends ServiceImpl<QrtzJobMapper, QrtzJob> implements IQrtzJobService {
    private final QuartzHandler quartzHandler;

    /**
     * 启动定时任务
     *
     * @param id 定时任务ID
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    @Override
    public void startJob(Integer id) throws BaseException {
        AssertObjUtils.assertNotNull(id, "ID不能为空");
        QrtzJob job = this.getById(id);
        try {
            quartzHandler.start(job);
            job.setStatus("执行中");
            this.updateById(job);
        } catch (SchedulerException e) {
            throw Exceptions.formatBaseException(e, "启动定时任务:[{}]失败", job.getJobName());
        } catch (ClassNotFoundException e) {
            throw Exceptions.formatBaseException(e, "任务对应的类:[{}]不存在", job.getBeanClass());
        }
    }


    /**
     * 暂停定时任务
     *
     * @param id 定时任务ID
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    @Override
    public void pauseJob(Integer id) throws BaseException {
        Assert.notNull(id, "ID不能为空");
        QrtzJob queryJob = this.getById(id);
        Assert.notNull(queryJob, "定时任务不存在");

        String status;
        try {
            status = quartzHandler.getStatus(queryJob);
        } catch (SchedulerException e) {
            throw Exceptions.baseException("获取定时任务状态出错");
        }
        if (!((Trigger.TriggerState.NORMAL.toString()).equals(status) || (Trigger.TriggerState.PAUSED.toString()).equals(status)
                || (Trigger.TriggerState.BLOCKED.toString()).equals(status))) {
            throw Exceptions.baseException("当前定时任务:[{}]不可暂停", queryJob.getJobName());
        }
        //noinspection StatementWithEmptyBody
        if ((Trigger.TriggerState.PAUSED.toString()).equals(status)) {
        } else {
            try {
                quartzHandler.pasue(queryJob);
            } catch (SchedulerException e) {
                throw Exceptions.formatBaseException(e, "暂停定时任务:[{}]出错", queryJob.getJobName());
            }
        }
        queryJob.setStatus("暂停");
        this.updateById(queryJob);
    }

    /**
     * 查询任务
     *
     * @param request 请求条件
     * @return cn.ac.iscas.dmo.templet.view.table.TableResponseData
     * @date 2022/3/14
     * @since jdk11
     */
    @Override
    public TableResponseData select(TableSearchRequest request) {
        Integer pageNumber = request.getPageNumber();
        Integer pageSize = request.getPageSize();
        // todo 过滤条件，暂时未处理
        @SuppressWarnings("unused") Object filter = request.getFilter();

        Page<QrtzJob> page = new Page<>(pageNumber, pageSize);
        page = this.page(page, null);

        TableResponseData responseData = new TableResponseData();
        responseData.setData(page.getRecords());
        responseData.setRows(page.getTotal());
        return responseData;
    }

    /**
     * 更新定时任务，只能修改cron表达式
     *
     * @param job 定时任务
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    @Override
    public void renew(QrtzJob job) throws BaseException {
        AssertObjUtils.assertNotNull(job.getId(), "ID不能为空");
        // todo 设置更新人

        LocalDateTime now = LocalDateTime.now();
        job.setUpdateTime(now);
        this.updateById(job);
        String status;
        try {
            status = quartzHandler.getStatus(job);
        } catch (SchedulerException e) {
            throw Exceptions.formatBaseException(e, "获取任务:[{}]状态失败", job.getJobName());
        }
        if (!(Trigger.TriggerState.NONE.toString()).equals(status)) {
            try {
                quartzHandler.updateCronExpression(job);
            } catch (SchedulerException e) {
                throw Exceptions.formatBaseException(e, "修改任务:[{}]表达式失败", job.getJobName());
            }
        }
    }

    /**
     * 删除定时任务
     *
     * @param id 定时任务ID
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    @Override
    public void delete(Integer id) throws BaseException {

        AssertObjUtils.assertNotNull(id, "ID不能为空");
        QrtzJob job = this.getById(id);
        String status;
        try {
            status = quartzHandler.getStatus(job);
        } catch (SchedulerException e) {
            throw Exceptions.formatBaseException(e, "获取任务:[{}]状态失败", job.getJobName());
        }
        if (!(Trigger.TriggerState.NONE.toString()).equals(status)) {
            try {
                quartzHandler.delete(job);
            } catch (SchedulerException e) {
                throw Exceptions.formatBaseException(e, "删除任务:[{}]出错", job.getJobName());
            }
        }

        // 删除任务表
        this.removeById(job.getId());
    }

    /**
     * 立即触发执行一个定时任务
     *
     * @param id 定时任务ID
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    @Override
    public void trigger(Integer id) throws BaseException {
        AssertObjUtils.assertNotNull(id, "ID不能为空");
        QrtzJob queryJob = this.getById(id);
        Assert.notNull(queryJob, "定时任务不存在");

        String status;
        try {
            status = quartzHandler.getStatus(queryJob);
        } catch (SchedulerException e) {
            throw Exceptions.formatBaseException(e, "获取任务:[{}]状态失败", queryJob.getJobName());
        }
        if (!((Trigger.TriggerState.NORMAL.toString()).equals(status) || (Trigger.TriggerState.PAUSED.toString()).equals(status)
                || (Trigger.TriggerState.COMPLETE.toString()).equals(status))) {
            throw Exceptions.formatBaseException("当前任务:[{}]不可立即执行", queryJob.getJobName());
        }

        try {
            quartzHandler.trigger(queryJob);
        } catch (SchedulerException e) {
            Exceptions.baseException("触发任务:[{}]执行出错", e);
        }
    }

    /**
     * 判断定时器是否是待机模式
     *
     * @return boolean
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    @Override
    public boolean isInStandbyMode() throws BaseException {
        try {
            return quartzHandler.isInStandbyMode();
        } catch (SchedulerException e) {
            throw Exceptions.baseException("判断调度器是否为待机状态出错", e);
        }
    }

    /**
     * 启动定时器
     *
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    @Override
    public void startScheduler() throws BaseException {
        try {
            quartzHandler.startScheduler();
        } catch (SchedulerException e) {
            throw Exceptions.baseException("启动定时器出错", e);
        }
    }

    /**
     * 待机定时器
     *
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    @Override
    public void standbyScheduler() throws BaseException {
        try {
            quartzHandler.startScheduler();
        } catch (SchedulerException e) {
            throw Exceptions.baseException("待机定时器出错", e);
        }
    }

    @Override
    public LocalDateTime getNextFireTime(String cronExpression) throws BaseException {
        try {
            return quartzHandler.getNextFireTime(cronExpression);
        } catch (ParseException e) {
            throw Exceptions.baseException("获取定时任务执行时间出错", e);
        }
    }
}
