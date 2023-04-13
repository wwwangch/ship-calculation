package com.iscas.biz.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.base.biz.schedule.CronTaskRegister;
import com.iscas.base.biz.schedule.SchedulingRunnable;
import com.iscas.base.biz.util.CacheUtils;
import com.iscas.base.biz.util.JWTUtils;
import com.iscas.biz.domain.common.LogInfo;
import com.iscas.biz.domain.common.Param;
import com.iscas.biz.mapper.common.LogInfoMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.service.common.LogInfoService;
import com.iscas.biz.service.common.ParamService;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.templet.exception.AuthenticationRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/20 18:27
 * @since jdk1.8
 */
@Service
@ConditionalOnMybatis
public class LogInfoServiceImpl extends ServiceImpl<LogInfoMapper, LogInfo> implements LogInfoService {
    @Autowired
    private CronTaskRegister cronTaskRegister;

    @Autowired
    private ParamService paramService;

    @Override
    public void deleteDataByTime(String holdPeriod) {

        holdPeriod = checkAndCachePeriod(holdPeriod);
        if (StringUtils.isBlank(holdPeriod)) {
            return;
        }

        SchedulingRunnable task = new SchedulingRunnable("clearLogTask", "clearLog", holdPeriod);
        //每天执行一次任务
        cronTaskRegister.addCronTask("clear_logInfo_task", task, "0 0 0 * * ?");

    }

    private String checkAndCachePeriod(String holdPeriod) {
        if (StringUtils.isBlank(holdPeriod)) {
            //period为空时，获取
            return getPeriodFromCache();
        } else {
            //period不为空时，更新
            return cachePeriod(holdPeriod);
        }
    }

    private String cachePeriod(String holdPeriod) {

        //写入数据库
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("param_key", "sys.log.holdPeriod");
        Param param = paramService.getOne(wrapper);
        String username;
        try {
            username = JWTUtils.getLoginUsername();
        } catch (AuthenticationRuntimeException e) {
            username = "unknown";
        }
        if (param == null) {
            Param data = new Param()
                    .setParamName("系统监控-访问日志-保留时长")
                    .setParamKey("sys.log.holdPeriod")
                    .setParamValue(holdPeriod)
                    .setParamType("系统类")
                    .setParamDesc("根据该参数清除之前的访问日志数据")
                    .setCreateBy(username)
                    .setCreateTime(DateSafeUtils.format(new Date()));
            paramService.save(data);
        } else {
            if (param.getParamValue().equals(holdPeriod)) {
                //清理周期没有改变
                return "";
            }
            Param updateParam = new Param()
                    .setParamValue(holdPeriod)
                    .setUpdateBy(username)
                    .setUpdateTime(DateSafeUtils.format(new Date()));
            paramService.update(updateParam, wrapper);
        }
        //放入缓存
        CacheUtils.putCache("param", param.getParamKey(), holdPeriod);

        return holdPeriod;
    }

    private String getPeriodFromCache() {
        String holdPeriod = paramService.getParamValue("sys.log.holdPeriod");
        //不存在时，赋值默认值，1天
        return Optional.ofNullable(holdPeriod).map(period -> period.toString()).orElse("1d");
    }

}
