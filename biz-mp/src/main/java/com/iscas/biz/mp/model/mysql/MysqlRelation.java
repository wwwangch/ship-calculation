package com.iscas.biz.mp.model.mysql;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/30 16:13
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class MysqlRelation {
    private String constrainName;
    private String childTableName;
    private String mainTableName;
    private List<String> childCols = new ArrayList<>();
    private List<String> mainCols = new ArrayList<>();
}
