package com.iscas.biz.mp.model.mysql;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/30 14:38
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class MysqlConstraint {
    private String tableName;
    private String constraint;
    private ConstraintEnum constraintType;
    private List<String> colNames = new ArrayList<>();


    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    public enum ConstraintEnum {
        P,R,U
    }
}
