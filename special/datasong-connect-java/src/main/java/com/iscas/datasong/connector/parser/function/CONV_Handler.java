package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * CONV(x,f1,f2) 返回 f1 进制数变成 f2 进制数
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/25 8:47
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "AlibabaClassNamingShouldBeCamel"})
public class CONV_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持函数:CONV");
    }
}
