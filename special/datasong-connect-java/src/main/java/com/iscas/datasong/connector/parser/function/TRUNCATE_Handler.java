package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/**
 * TRUNCATE(x,y) 返回数值 x 保留到小数点后 y 位的值（与 ROUND 最大的区别是不会进行四舍五入）
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/12 09:17
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "unused", "AlibabaClassNamingShouldBeCamel"})
public class TRUNCATE_Handler implements FunctionHandler {

    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Object result = 0;
                Expression exp1 = expressions.get(0);
                Expression exp2 = expressions.get(1);
                Object first = getData(data, exp1);
                Object second = getData(data, exp2);
                if (first != null && second != null) {
                    double x = 0d;
                    try {
                        x = Double.parseDouble(first.toString());
                    } catch (Exception ignored) {
                    }
                    int len = 0;
                    try {
                        len = Integer.parseInt(second.toString());
                    } catch (Exception ignored) {
                    }
                    BigDecimal bigDecimal = new BigDecimal(x);
                    bigDecimal = bigDecimal.setScale(len, RoundingMode.DOWN);
                    result = bigDecimal.doubleValue();
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
