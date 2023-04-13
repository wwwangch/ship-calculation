package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * MONTHNAME(d) 	返回日期当中的月份名称，如 November
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 8:57
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class MONTHNAME_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Object result = 0;
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                if (first != null) {
                    Date date = getDate(first);
                    if (date != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        Integer month = calendar.get(Calendar.MONTH);
                        result = getMonth(month);
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
