package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 17:07
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class SECOND_Handler implements FunctionHandler {
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
                    Date date;
                    try {
                        date = DateSafeUtils.parse(first.toString(), "HH:mm:ss");
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    if (date != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        result = calendar.get(Calendar.SECOND);
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
