package com.iscas.common.prometheus.tools.model.rule;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/31 14:30
 */
@Data
public class Group implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 文件
     */
    private String file;

    /**
     * 规则
     */
    private List<Rule> rules;

    /**
     * interval
     */
    private Integer interval;

    /**
     * limit
     */
    private Integer limit;

    /**
     * evaluationTime
     */
    private Double evaluationTime;

    /**
     * lastEvaluation
     */
    private LocalDateTime lastEvaluation;

}
