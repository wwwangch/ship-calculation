package com.iscas.biz.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/29 9:07
 * @since jdk1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "api.prefix")
public class ApiPrefix {
    private String v1;
}
