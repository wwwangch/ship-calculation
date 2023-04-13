package com.iscas.biz.test.datasong.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/8 8:24
 * @since jdk11
 */
@TableName("testtable")
@Data
public class TestTable {
    @TableId(value = "_id"/*, type= IdType.AUTO*/)
    private String dsId;
//    @TableField("id")
//    private Integer id;

    private String name;

    @TableField("id")
    private Integer id2;

    @TableField("add_col4")
    private String addcol4;
}
