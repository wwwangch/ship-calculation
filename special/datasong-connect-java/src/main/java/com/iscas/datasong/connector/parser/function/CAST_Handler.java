package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * CAST(x AS type) 转换数据类型
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/21 16:19
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class CAST_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持方法：CAST");
    }
}
