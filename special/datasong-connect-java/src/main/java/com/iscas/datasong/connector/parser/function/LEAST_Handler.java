package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * LEAST(expr1, expr2, expr3, ...) 返回列表中的最小值
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/11 13:41
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "unused", "AlibabaClassNamingShouldBeCamel"})
public class LEAST_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object min = getData(data, exp1);
                for (int i = 1; i < expressions.size(); i++) {
                    Expression expi = expressions.get(i);
                    Object obji = getData(data, expi);
                    min = min(min, obji);
                }
                if (alias != null) {
                    data.put(alias.getName(), min);
                } else {
                    data.put(func.toString(), min);
                }
            }
        }
    }

    private Object min(Object min, Object obji) {
        if (min != null && obji != null) {
            // 如果都是字符串比较字符自然顺序，如果其中有一个是数字，比较数值大小
            if (min instanceof String) {
                if (obji instanceof String) {
                    if (min.toString().compareTo(obji.toString()) > 0) {
                        return obji;
                    }
                    return min;
                }
                return min;
            } else {
                try {
                    double minD = Double.parseDouble(min.toString());
                    if (obji instanceof String) {
                        return obji;
                    }
                    try {
                        double objD = Double.parseDouble(obji.toString());
                        return minD - objD < 0 ? min : obji;
                    } catch (Exception ignored2) {
                        return null;
                    }
                } catch (Exception ignored) {
                    return null;
                }
            }
        }
        return null;
    }
}
