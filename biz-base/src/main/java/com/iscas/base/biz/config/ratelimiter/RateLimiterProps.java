package com.iscas.base.biz.config.ratelimiter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 限流属性
 *
 * @author zhuquanwen
 */
@ConfigurationProperties(prefix = "rate.limiter")
@Data
public class RateLimiterProps {

    /**
     * 每秒产生令牌数
     */
    private double permitsPerSecond = 20;

    /**
     * 获取令牌最大等待时间毫秒数
     */
    @DurationUnit(ChronoUnit.MILLIS)
    private Duration maxWait = Duration.ofMillis(500);

    /**
     * 静态资源
     */
    private List<String> staticUrl;

}
