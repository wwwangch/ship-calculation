package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.text.ParseException;
import java.util.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/21 14:26
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class SEC_TO_TIME_Handler implements FunctionHandler{
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Object result = "00:00:00";
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                if (first != null) {
                    long sec = 0L;
                    try {
                        sec = Integer.parseInt(first.toString()) * 1000L;
                    } catch (Exception ignored) {}
                    result = DateSafeUtils.format(new Date(sec), "HH:mm:ss", TimeZone.getTimeZone("UTC"));
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
