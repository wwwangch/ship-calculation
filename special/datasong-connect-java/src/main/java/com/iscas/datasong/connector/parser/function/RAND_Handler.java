package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.security.SecureRandom;
import java.util.Map;

/**
 * RAND() 返回 0 到 1 的随机数
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/11 19:12
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "unused", "AlibabaClassNamingShouldBeCamel"})
public class RAND_Handler implements FunctionHandler {
    private final SecureRandom random = new SecureRandom();
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        double result = random.nextDouble();
        if (alias != null) {
            data.put(alias.getName(), result);
        } else {
            data.put(func.toString(), result);
        }
    }
}
