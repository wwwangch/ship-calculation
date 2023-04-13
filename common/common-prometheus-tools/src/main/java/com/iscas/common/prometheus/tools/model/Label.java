package com.iscas.common.prometheus.tools.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/31 10:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Label implements Serializable {
    private String key;
    private String value;
}
