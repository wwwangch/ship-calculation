package com.iscas.log;

import ch.qos.logback.classic.spi.LoggingEvent;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/3/6 11:22
 * @since jdk1.8
 */
public class QueueEntity {

    private LoggingEvent event;
    private String hostName;
    private String appIp;



    public LoggingEvent getEvent() {
        return event;
    }

    public void setEvent(LoggingEvent event) {
        this.event = event;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    @Override
    public String toString() {
        return "QueueEntity{" +
                ", event=" + event +
                ", hostName='" + hostName + '\'' +
                ", appIp='" + appIp + '\'' +
                '}';
    }
}
