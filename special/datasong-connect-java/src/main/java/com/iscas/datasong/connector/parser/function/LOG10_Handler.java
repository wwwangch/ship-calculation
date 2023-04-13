package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * LOG10(x) 返回以 10 为底的对数
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/11 16:03
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "unused", "AlibabaClassNamingShouldBeCamel"})
public class LOG10_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Object result = null;

                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                if (first != null) {
                    double d = 0;
                    try {
                        d = Double.parseDouble(first.toString());
                    } catch (Exception ignored) {
                    }
                    if (d > 0) {
                        result = Math.log(d) / Math.log(10);
                    }
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
