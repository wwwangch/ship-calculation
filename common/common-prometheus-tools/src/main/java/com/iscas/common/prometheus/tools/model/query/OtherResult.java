package com.iscas.common.prometheus.tools.model.query;

import lombok.Data;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 17:16
 */
@Data
public class OtherResult extends Result {
    /**
     * 其他数据
     * */
    private Object other;
}
