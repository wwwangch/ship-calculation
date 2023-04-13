package com.iscas.base.biz.config.datasongplus;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 限流属性
 * */
@ConfigurationProperties(prefix = "datasong.client.plus")
@Data
public class DatasongPlusProps {
    /**配置的数组*/
    private List<String> packages;
}
