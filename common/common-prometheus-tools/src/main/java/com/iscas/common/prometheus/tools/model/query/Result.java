package com.iscas.common.prometheus.tools.model.query;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 16:42
 */
@Data
public class Result implements Serializable {

    /**
     * metric
     * */
    private List<Metric> metric;
}
