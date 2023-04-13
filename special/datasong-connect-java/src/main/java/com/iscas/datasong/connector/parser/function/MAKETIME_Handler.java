package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 17:18
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class MAKETIME_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Expression exp2 = expressions.get(1);
                Expression exp3 = expressions.get(2);
                Object first = getData(data, exp1);
                Object second = getData(data, exp2);
                Object third = getData(data, exp3);
                String result = null;
                if (first != null && second != null && third != null) {
                    Integer hour = null;
                    Integer minute = null;
                    Integer seconds = null;
                    try {
                        hour = Integer.parseInt(first.toString());
                        minute = Integer.parseInt(second.toString());
                        seconds = Integer.parseInt(third.toString());
                    } catch (Exception ignored) {
                    }
                    if (hour != null && minute != null && seconds != null) {
                        result = hour + ":" + minute + ":" + seconds;
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
