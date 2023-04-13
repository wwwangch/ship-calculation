package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * IF(expr,v1,v2) 如果表达式 expr 成立，返回结果 v1；否则，返回结果 v2。
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/25 8:55
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class IF_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持函数:IF");
    }
}
