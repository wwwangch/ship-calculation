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
 * FROM_DAYS(n) 计算从 0000 年 1 月 1 日开始 n 天后的日期
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 16:42
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class FROM_DAYS_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Object result = "0000-01-01";
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                if (first != null) {
                    int offset = 0;
                    try {
                        offset = Integer.parseInt(first.toString());
                    } catch (Exception ignored) {
                    }
                    try {
                        Date date = DateSafeUtils.parse(result.toString(), "yyyy-MM-dd");
                        long time = date.getTime() + offset * 24 * 3600 * 1000L;
                        result = DateSafeUtils.format(new Date(time), "yyyy-MM-dd");
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
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
