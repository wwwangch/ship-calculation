package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * COS(x) 求余弦值(参数是弧度)
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 15:48
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "JavadocDeclaration", "unused"})
public class COS_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                Object result = 1;
                if (first != null) {
                    double n = 0;
                    try {
                        n = Double.parseDouble(first.toString());
                    } catch (Exception ignored) {
                    }
                    result = Math.cos(n);
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