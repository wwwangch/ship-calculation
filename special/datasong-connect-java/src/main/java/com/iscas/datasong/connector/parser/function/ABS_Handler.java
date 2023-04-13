package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * ABS(x) 返回 x 的绝对值
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 15:12
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class ABS_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                Object result = 0L;
                if (first != null) {
                    if (first instanceof Integer) {
                        result = Math.abs((Integer) first);
                    } else if (first instanceof Long) {
                        result = Math.abs((Long) first);
                    } else if (first instanceof Float) {
                        result = Math.abs((Float) first);
                    } else if (first instanceof Double) {
                        result = Math.abs((Double) first);
                    } else {
                        String str = first.toString();
                        try {
                            long l = Long.parseLong(str);
                            result = Math.abs(l);
                        } catch (Exception ignored) {
                            try {
                                double d = Double.parseDouble(str);
                                result = Math.abs(d);
                            } catch (Exception ignored2) {
                            }
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