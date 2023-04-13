package com.iscas.biz.mp.table.model;

import lombok.Data;

/**
 * @author DataDong
 * @date Create in 2018/9/11 8:59
 */
@Data
public class TableDefinition {
    private Integer id;
    private String tableIdentity;
    private String title;
    private String databaseName;
    private String tableName;
    private String sql;
    private Boolean checkbox = false;
    private String backInfo;
    private String frontInfo;
    private String viewType;
    private String cellEditable;
    private String primaryKey;
    private String buttonSetting;
}
