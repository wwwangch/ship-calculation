package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * STR_TO_DATE(string, format_mask) 将字符串转变为日期
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 17:22
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class STR_TO_DATE_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持方法：STR_TO_DATE");
    }
}
