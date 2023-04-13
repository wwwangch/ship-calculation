package com.iscas.biz.schedule;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.iscas.base.biz.util.RegexUtils;
import com.iscas.biz.mapper.common.LogInfoMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.common.tools.core.date.DateSafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/25 14:44
 * @since jdk1.8
 * 清理之前的log
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
//@Component
@Slf4j
@ConditionalOnMybatis
public class ClearLogTask {

    @Autowired
    private LogInfoMapper logInfoMapper;

    public void clearLog(String holdPeriod) {

        String clearBefore = getClearDateByPeriod(holdPeriod);
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.le("operate_time", clearBefore);
        logInfoMapper.delete(wrapper);
    }

    @SuppressWarnings({"AlibabaUndefineMagicConstant", "StatementWithEmptyBody"})
    private String getClearDateByPeriod(String cleanPeriod) {

        String period = RegexUtils.getStartNumber(cleanPeriod);
        String unit = cleanPeriod.replace(period, "");
        long time = Long.parseLong(period);

        if ("s".equalsIgnoreCase(unit)) {
        } else if ("m".equalsIgnoreCase(unit)) {
            time = time * 60;
        } else if ("h".equalsIgnoreCase(unit)) {
            time = time * 60 * 60;
        } else if ("d".equalsIgnoreCase(unit)) {
            time = time * 60 * 60 * 24;
        }
        return DateSafeUtils.format(new Date(System.currentTimeMillis() - time));
    }

}
