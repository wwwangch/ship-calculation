package com.iscas.common.prometheus.tools.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 16:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Value implements Serializable {
    private double time;
    private String val;
}
