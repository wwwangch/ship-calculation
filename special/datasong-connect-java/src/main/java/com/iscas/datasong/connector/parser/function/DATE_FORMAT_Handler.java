package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * DATE_FORMAT(d,f) 按表达式 f的要求显示日期 d
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 10:19
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class DATE_FORMAT_Handler implements FunctionHandler {
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
                    Date date = getDate(first);
                    String format = second.toString().replace("%Y", "yyyy")
                            .replace("%m", "MM")
                            .replace("%M", "MM")
                            .replace("%d", "dd")
                            .replace("%D", "dd")
                            .replace("%H", "HH")
                            .replace("%h", "hh")
                            .replace("%S", "ss")
                            .replace("%s", "ss")
                            .replace("%y", "yy");
                    result = DateSafeUtils.format(date, format);
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
