package com.iscas.common.tools.office.excel.easyexcel.read;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/24 10:24
 * @since jdk1.8
 */
@Data
public class EasyExcelNameDemoVO {
    @ExcelProperty("字符串")
    private String str;
    @ExcelProperty("日期")
    private Date date;
    @ExcelProperty("数字")
    private double d;

}
