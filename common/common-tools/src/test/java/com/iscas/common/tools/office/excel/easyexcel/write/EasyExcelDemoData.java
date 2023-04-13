package com.iscas.common.tools.office.excel.easyexcel.write;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/24 12:09
 * @since jdk1.8
 */
@Data
@AllArgsConstructor
public class EasyExcelDemoData {
    @ExcelProperty(value = "字符串", index = 0)
    private String str;
    @ExcelProperty(value = "日期", index = 1)
    private Date date;
    @ExcelProperty(value = "数值", index = 2)
    private double d;
    private String toIgnore;
}
