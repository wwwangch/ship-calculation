package com.iscas.base.biz.config.health;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认健康检测处理
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/10 15:20
 * @since jdk1.8
 */
@Slf4j
public class DefaultHealthCheckHandler implements IHealthCheckHandler {

    @Override
    public void readinessAccept() {
        log.info("健康检测-readiness-检测成功-服务已准备好,可以提供服务");
    }

    @Override
    public void readinessRefuse() {
        log.info("健康检测-readiness-检测失败-服务未准备好或即将关闭");
    }

    @Override
    public void livenessCorrect() {
        log.info("健康检测-liveness-检测成功-服务正常");
    }

    @Override
    public void livenessBroken() {
        log.info("健康检测-liveness-检测失败-服务异常");

    }
}
