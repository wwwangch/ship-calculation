package com.iscas.base.biz.config.health;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.event.EventListener;


/**
 * @author zhuquanwen
 */
@SuppressWarnings("unused")
public class LivenessStateListener extends HealthBaseListener {

    @EventListener
    public void onStateChange(AvailabilityChangeEvent<LivenessState> event) {
        switch (event.getState()) {
            // create file /tmp/healthy
            case CORRECT -> getHealthCheckHandler().livenessCorrect();
            // remove file /tmp/healthy
            case BROKEN -> getHealthCheckHandler().livenessBroken();
            default -> {}
        };
    }
}