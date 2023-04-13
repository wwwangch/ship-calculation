package com.iscas.biz.mp.model.mysql;

import com.iscas.biz.mp.model.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/30 9:55
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MysqlTable extends Table {
    private String schema;
    private String type;
    private String engine;
    private String version;
    private String rowFormat;
    private long rows;
    private Date createTime;
    private Date updateTime;
    private String collation;
}
