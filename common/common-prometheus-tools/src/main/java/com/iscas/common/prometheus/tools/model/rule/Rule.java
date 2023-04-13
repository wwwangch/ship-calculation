package com.iscas.common.prometheus.tools.model.rule;

import com.iscas.common.prometheus.tools.model.Annotation;
import com.iscas.common.prometheus.tools.model.Label;
import com.iscas.common.prometheus.tools.model.alert.Alert;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/31 14:27
 */
@Data
public class Rule implements Serializable {
    /**
     * 状态
     */
    private String state;

    /**
     * 名称
     */
    private String name;

    /**
     * 条件
     */
    private String query;

    /**
     * 持续时间
     */
    private Integer duration;

    /**
     * 标签
     */
    private List<Label> labels;

    /**
     * 注解
     */
    private List<Annotation> annotations;

    /**
     * 告警
     */
    private List<Alert> alerts;

    /**
     * 健康情况
     */
    private String health;

    /**
     * evaluationTime
     */
    private Double evaluationTime;

    /**
     * lastEvaluation
     */
    private LocalDateTime lastEvaluation;

    /**
     * 类型
     */
    private String type;

}
