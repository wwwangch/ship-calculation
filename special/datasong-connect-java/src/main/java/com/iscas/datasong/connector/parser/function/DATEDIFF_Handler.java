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
 * DATEDIFF(d1,d2) 计算日期 d1->d2 之间相隔的天数
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 10:19
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class DATEDIFF_Handler implements FunctionHandler {
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
                Object result = null;
                if (first != null && second != null) {
                    Date date1 = getDate(first);
                    Date date2 = getDate(second);
                    if (date1 != null && date2 != null) {
                        long time = date1.getTime() - date2.getTime();
                        long dateDiff =  time / 1000L / 60 / 60 / 24;
                        result = (int) dateDiff;
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
