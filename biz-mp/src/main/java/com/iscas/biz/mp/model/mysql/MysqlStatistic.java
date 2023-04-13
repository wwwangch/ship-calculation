package com.iscas.biz.mp.model.mysql;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/30 15:35
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class MysqlStatistic {
    private String schema;
    private String tableName;
    private String indexName;
    private String columnName;
    private Integer seq;
}
