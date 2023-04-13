package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * ISNULL(expression) 判断表达式是否为 NULL
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/25 9:02
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class ISNULL_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Object result;
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                if (first != null) {
                    result = 0;
                } else {
                    result = 1;
                }
                if (alias != null) {
                    data.put(alias.getName(), result);
                } else {
                    data.put(func.toString(), result);
                }
            }
        }
    }
}
