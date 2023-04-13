package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * REPEAT(s,n) 将字符串 s 重复 n 次
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 10:29
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "unused"})
public class REPEAT_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Expression exp2 = expressions.get(1);
                Object first = getData(data, exp1);
                Object second = getData(data, exp2);
                String result = "";
                if (first != null && second != null) {
                    int repeatCount = -1;
                    try {
                        repeatCount = Integer.parseInt(second.toString());
                    } catch (Exception ignored) {}
                    if (repeatCount > 0) {
                        result = first.toString().repeat(repeatCount);
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
