package com.iscas.base.biz.config.elasticjob.v3;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务配置
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/16 11:13
 * @since jdk1.8
 */

@ConditionalOnElasticJob
@Data
@Component
@ConfigurationProperties(prefix = "elastic-job")
public class ElasticJobEntityConfig {
    private List<ElasticJobEntity> jobs = new ArrayList<>();
}
