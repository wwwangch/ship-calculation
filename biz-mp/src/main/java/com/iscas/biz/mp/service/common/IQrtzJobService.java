package com.iscas.biz.mp.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.biz.mp.model.QrtzJob;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.view.table.TableResponseData;
import com.iscas.templet.view.table.TableSearchRequest;

import java.time.LocalDateTime;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/26 10:21
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused"})
public interface IQrtzJobService extends IService<QrtzJob> {
    /**
     * 启动定时任务
     *
     * @param id 定时任务ID
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    void startJob(Integer id) throws BaseException;


    /**
     * 暂停定时任务
     *
     * @param id 定时任务ID
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    void pauseJob(Integer id) throws BaseException;

    /**
     * 查询任务
     *
     * @param request 请求条件
     * @return cn.ac.iscas.dmo.templet.view.table.TableResponseData
     * @date 2022/3/14
     * @since jdk11
     */
    TableResponseData select(TableSearchRequest request);

    /**
     * 更新定时任务，只能修改cron表达式
     *
     * @param job 定时任务
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    void renew(QrtzJob job) throws BaseException;

    /**
     * 删除定时任务
     *
     * @param id 定时任务ID
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    void delete(Integer id) throws BaseException;

    /**
     * 立即触发执行一个定时任务
     *
     * @param id 定时任务ID
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    void trigger(Integer id) throws BaseException;

    /**
     * 判断定时器是否是待机模式
     *
     * @return boolean
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    boolean isInStandbyMode() throws BaseException;

    /**
     * 启动定时器
     *
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    void startScheduler() throws BaseException;

    /**
     * 待机定时器
     *
     * @throws BaseException 异常
     * @date 2022/3/14
     * @since jdk11
     */
    void standbyScheduler() throws BaseException;

    /**
     * 获取下次执行时间
     *
     * @param cronExpression CRON表达式
     * @return java.time.LocalDateTime
     * @throws BaseException 异常
     * @date 2022/4/22
     * @since jdk11
     */
    LocalDateTime getNextFireTime(String cronExpression) throws BaseException;
}
