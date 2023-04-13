package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * LOG(x) 或 LOG(base, x)   	返回自然对数(以 e 为底的对数)，如果带有 base 参数，则 base 为指定带底数。　
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/11 13:45
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "unused", "AlibabaClassNamingShouldBeCamel"})
public class LOG_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Object result = null;
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                if (expressions.size() != 1) {
                    Expression exp2 = expressions.get(1);
                    Object second = getData(data, exp2);
                    if (first != null && second != null) {
                        double base = 0;
                        try {
                            base = Double.parseDouble(first.toString());
                        } catch (Exception ignored) {}
                        double x = 0;
                        try {
                            x = Double.parseDouble(second.toString());
                        } catch (Exception ignored) {}
                        if (base > 0 && x > 0) {
                            result = Math.log(x) / Math.log(base);
                        }
                    }
                } else {
                    if (first != null) {
                        double d = 0;
                        try {
                            d = Double.parseDouble(first.toString());
                        } catch (Exception ignored) {}
                        if (d > 0) {
                            result = Math.log(d);
                        }
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
