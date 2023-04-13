package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * PERIOD_DIFF(period1, period2) 返回两个时段之间的月份差值
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 9:13
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class PERIOD_DIFF_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持方法PERIOD_DIFF");
    }
}
