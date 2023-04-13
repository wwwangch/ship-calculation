package com.iscas.base.biz.config.health;

/**
 * 健康检测处理扩展接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/10 15:18
 * @since jdk1.8
 */
public interface IHealthCheckHandler {

    /**健康检测-readiness检测成功*/
    void readinessAccept();

    /**健康检测-readiness检测失败*/
    void readinessRefuse();

    /**健康检测-liveness检测成功*/
    void livenessCorrect();

    /**健康检测-liveness检测失败*/
    void livenessBroken();

}
