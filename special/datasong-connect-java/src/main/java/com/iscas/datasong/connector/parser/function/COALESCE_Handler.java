package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * COALESCE(expr1, expr2, ...., expr_n) 返回参数中的第一个非空表达式（从左向右）
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/21 16:22
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class COALESCE_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            Object result = null;
            if (CollectionUtil.isNotEmpty(expressions)) {
                result = expressions.stream()
                        .map(exp -> getData(data, exp))
                        .filter(Objects::nonNull)
                        .findFirst().orElse(null);
            }
            if (alias != null) {
                data.put(alias.getName(), result);
            } else {
                data.put(func.toString(), result);
            }
        }
    }

}
