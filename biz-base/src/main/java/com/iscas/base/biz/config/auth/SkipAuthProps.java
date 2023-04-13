package com.iscas.base.biz.config.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/28 18:51
 * @since jdk1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "skip.authentication")
public class SkipAuthProps {
    private List<String> urls = new ArrayList<>();
}
