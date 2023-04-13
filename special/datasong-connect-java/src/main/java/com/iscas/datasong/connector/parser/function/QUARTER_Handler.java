package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * QUARTER(d) 返回日期d是第几季节，返回 1 到 4
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 17:05
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class QUARTER_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持方法QUARTER");
    }
}
