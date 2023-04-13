package com.iscas.base.biz.config.health;

import com.iscas.base.biz.util.SpringUtils;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/10 15:46
 * @since jdk1.8
 */
public class HealthBaseListener {
    protected volatile IHealthCheckHandler healthCheckHandler = null;

    public IHealthCheckHandler getHealthCheckHandler() {
        if (healthCheckHandler == null) {
            synchronized (LivenessStateListener.class) {
                if (healthCheckHandler == null) {
                    healthCheckHandler = SpringUtils.getApplicationContext().getBean(IHealthCheckHandler.class);
                }
            }
        }
        return healthCheckHandler;
    }
}
