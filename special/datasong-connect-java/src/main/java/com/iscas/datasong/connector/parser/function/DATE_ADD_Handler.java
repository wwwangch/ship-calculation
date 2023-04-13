package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * DATE_ADD(d，INTERVAL expr type)
 * 计算起始日期 d 加上一个时间段后的日期，type 值可以是：
 * MICROSECOND
 * SECOND
 * MINUTE
 * HOUR
 * DAY
 * WEEK
 * MONTH
 * QUARTER
 * YEAR
 * SECOND_MICROSECOND
 * MINUTE_MICROSECOND
 * MINUTE_SECOND
 * HOUR_MICROSECOND
 * HOUR_SECOND
 * HOUR_MINUTE
 * DAY_MICROSECOND
 * DAY_SECOND
 * DAY_MINUTE
 * DAY_HOUR
 * YEAR_MONTH
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 10:19
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class DATE_ADD_Handler implements FunctionHandler {
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
                if (first != null && exp2 != null) {
                    Date date = getDate(first);
                    if (exp2 instanceof IntervalExpression) {
                        long intervalMs = getIntervalMs(date, (IntervalExpression) exp2, false);
                        result = DateSafeUtils.format(new Date(intervalMs));
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
