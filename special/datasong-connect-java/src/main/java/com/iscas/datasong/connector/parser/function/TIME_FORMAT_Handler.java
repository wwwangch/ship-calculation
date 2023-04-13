package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * TIME_FORMAT(t,f) 按表达式 f 的要求显示时间 t
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 17:31
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class TIME_FORMAT_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持方法:TIME_FORMAT");
    }
}
