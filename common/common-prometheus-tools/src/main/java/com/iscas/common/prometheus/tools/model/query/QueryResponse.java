package com.iscas.common.prometheus.tools.model.query;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 16:37
 */
@Data
public class QueryResponse<T extends Result> implements Serializable {
    /**
     * 类型
     */
    private QueryResultType resultType;

    /**
     * 结果
     * */
    private List<T> result;


}
