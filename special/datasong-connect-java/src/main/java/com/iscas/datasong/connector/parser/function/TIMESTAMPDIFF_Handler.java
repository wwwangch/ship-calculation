package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * TIMESTAMPDIFF(unit,datetime_expr1,datetime_expr2) 计算时间差，返回 datetime_expr2 − datetime_expr1 的时间差
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/18 9:37
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class TIMESTAMPDIFF_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持方法:TIMESTAMPDIFF");
    }
}
