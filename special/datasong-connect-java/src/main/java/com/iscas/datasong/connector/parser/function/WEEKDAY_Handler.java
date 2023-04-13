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
 * WEEKDAY(d) 日期 d 是星期几，0 表示星期一，1 表示星期二
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/18 10:09
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class WEEKDAY_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                Integer result = null;
                if (first != null) {
                    Date date = getDate(first);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    result = calendar.get(Calendar.DAY_OF_WEEK);
                    result = result - 2;
                    if (result < 0) {
                        result = 7 + result;
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
