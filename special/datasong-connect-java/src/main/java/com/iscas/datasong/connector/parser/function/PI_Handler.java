package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * pi() 	返回圆周率(3.141593）
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/11 16:20
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "unused", "AlibabaClassNamingShouldBeCamel"})
public class PI_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        double pi = 3.141593;
        if (alias != null) {
            data.put(alias.getName(), pi);
        } else {
            data.put(func.toString(), pi);
        }
    }
}
