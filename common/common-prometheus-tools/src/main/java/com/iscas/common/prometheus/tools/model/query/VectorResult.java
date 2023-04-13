package com.iscas.common.prometheus.tools.model.query;

import lombok.Data;

import java.util.List;

/**
 * vector结果
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 16:55
 */
@Data
public class VectorResult extends Result {
    /**
     * value
     */
    private Value value;
}
