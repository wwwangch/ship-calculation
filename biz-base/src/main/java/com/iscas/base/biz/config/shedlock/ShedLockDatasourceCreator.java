package com.iscas.base.biz.config.shedlock;

import javax.sql.DataSource;

/**
 * 构建ShedLockDatasourceCreator
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/20 19:46
 * @since jdk1.8
 */
public interface ShedLockDatasourceCreator {
    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    DataSource createDataSource();
}
