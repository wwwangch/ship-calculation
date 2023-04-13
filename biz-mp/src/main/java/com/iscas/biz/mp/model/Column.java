package com.iscas.biz.mp.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 列
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/30 8:57
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class Column {
    /**
     * 表名
     * */
    protected String tableName;

    /**
     * 列名
     * */
    protected String colName;

    /**
     * 默认值
     * */
    protected Object defaultVal;

    /**
     * 是否可以为空
     * */
    protected boolean nullable = true;

    /**
     * 数据类型
     * */
    protected String dataType;

    /**
     * 列的顺序
     * */
    protected Integer ordinalPosition;

    /**
     * 注释
     * */
    protected String comment;


}
