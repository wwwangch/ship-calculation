package com.iscas.biz.mp.table.model;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author DataDong
 * @date Create in 2018/9/13 15:25
 */
@Component
@Data
@ConditionalOnMybatis
public class TableDefinitionConfig {
    @Value("${iscas.table.table-definition-table}")
    private String tableDefinitionTableName;
    @Value("${iscas.table.header-definition-table}")
    private String headerDefinitionTableName;
    @Value("${iscas.table.primary-key}")
    private String primaryKey = null;
}
