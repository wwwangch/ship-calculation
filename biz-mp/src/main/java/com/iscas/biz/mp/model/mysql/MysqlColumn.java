package com.iscas.biz.mp.model.mysql;

import com.iscas.biz.mp.model.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * mysql的列结构
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/30 9:08
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MysqlColumn extends Column {
    /**
     * 库名
     * */
    private String tableSchema;

    private Long charMaxLen;

    private Long charOctetLen;

    private String charset;

    private String collation;

    private String columnType;

    private Long numPrecision;

    private Long numScale;

//    private Integer datetimePrecision;



}
