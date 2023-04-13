package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * GREATEST(expr1, expr2, expr3, ...) 返回列表中的最大值
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/11 8:42
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "unused", "AlibabaClassNamingShouldBeCamel"})
public class GREATEST_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object max = getData(data, exp1);
                for (int i = 1; i < expressions.size(); i++) {
                    Expression expi = expressions.get(i);
                    Object obji = getData(data, expi);
                    max = max(max, obji);
                }
                if (alias != null) {
                    data.put(alias.getName(), max);
                } else {
                    data.put(func.toString(), max);
                }
            }
        }
    }

    private Object max(Object max, Object obji) {
        if (max != null && obji != null) {
            // 如果都是字符串比较字符自然顺序，如果其中有一个是数字，比较数值大小
            if (max instanceof String) {
                if (obji instanceof String) {
                    if (max.toString().compareTo(obji.toString()) < 0) {
                        return obji;
                    }
                    return max;
                }
                return obji;
            } else {
                try {
                    double maxD = Double.parseDouble(max.toString());
                    if (obji instanceof String) {
                        return max;
                    }
                    try {
                        double objD = Double.parseDouble(obji.toString());
                        return maxD - objD > 0 ? max : obji;
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
