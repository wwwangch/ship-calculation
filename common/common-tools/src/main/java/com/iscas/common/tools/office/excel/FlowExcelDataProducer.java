package com.iscas.common.tools.office.excel;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/2 19:46
 * @since jdk1.8
 */
@FunctionalInterface
public interface FlowExcelDataProducer {

    /**
     * supply
     *
     * @param times     当前调用次数
     * @param sheetName sheet页名
     * @return cn.ac.iscas.dmo.common.tools.office.excel.ExcelUtils.ExcelResult
     * @date 2022/4/18
     * @since jdk11
     */
    @SuppressWarnings("rawtypes")
    ExcelUtils.ExcelResult supply(int times, String sheetName);
}
