package com.iscas.base.biz.config.health;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;

/**
 * @author zhuquanwen
 */
@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class ReadinessStateListener extends HealthBaseListener {

    @EventListener
    public void onStateChange(AvailabilityChangeEvent<ReadinessState> event) {
        switch (event.getState()) {
            // create file /tmp/healthy
            case ACCEPTING_TRAFFIC -> getHealthCheckHandler().readinessAccept();
            // remove file /tmp/healthy
            case REFUSING_TRAFFIC -> getHealthCheckHandler().readinessRefuse();
            default -> {}
        }
    }

}