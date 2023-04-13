package com.iscas.biz.schedule;

import com.iscas.biz.mapper.common.WsDataMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author zhuquanwen
 */
//@Component("ClearWsDataTask")
@Lazy(value = false)
@Slf4j
@ConditionalOnMybatis
public class ClearWsDataTask {

    @DurationUnit(ChronoUnit.HOURS)
    @Value("${ws.persistent.timeout}")
    private final Duration wsTimeout = Duration.ofHours(10);

    private final WsDataMapper wsDataMapper;

    public ClearWsDataTask(WsDataMapper wsDataMapper) {
        this.wsDataMapper = wsDataMapper;
    }

    /**
     * 定时任务
     * */
    public void clear() {
        log.debug("========定时清除持久化的消息============");
        long time = System.currentTimeMillis() - wsTimeout.getSeconds() * 1000;
        Date date = new Date(time);
        wsDataMapper.deleteByTime(date);
    }
}