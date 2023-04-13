package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * DATE() 从日期或日期时间表达式中提取日期值
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 10:19
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class DATE_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                Object result = null;
                if (first != null) {
                    Date date;
                    if (first instanceof Date) {
                        date = (Date) first;
                    } else {
                        try {
                            date = DateSafeUtils.parseStringToDate(first.toString());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    result = DateSafeUtils.format(date, "yyyy-MM-dd");
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
