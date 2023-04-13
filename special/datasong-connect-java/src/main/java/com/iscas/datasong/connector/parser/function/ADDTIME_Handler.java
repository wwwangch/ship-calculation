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
 * ADDTIME(t,n) n 是一个时间表达式，时间 t 加上时间表达式 n
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/12 09:17
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "unused", "AlibabaClassNamingShouldBeCamel"})
public class ADDTIME_Handler implements FunctionHandler {

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
                    try {
                        Date date;
                        if (first instanceof Date) {
                            date = (Date) first;
                        } else {
                            String dateStr = first.toString();
                            date = DateSafeUtils.parseStringToDate(dateStr);
                        }
                        String timeStr = second.toString();
                        Date addDate = null;
                        // 暂时认为第2个时间格式只能是秒数、HH:mm:ss、HH：mm
                        try {
                            int addSecond = Integer.parseInt(timeStr);
                            date = new Date(date.getTime() + addSecond * 1000L);
                        } catch (Exception e) {
                            try {
                                addDate = DateSafeUtils.parse(timeStr, "HH:mm:ss");
                                long addTime = addDate.getTime() - DateSafeUtils.parse("1970-01-01 00:00:00").getTime();
                                date = new Date(date.getTime() + addTime);
                            } catch (Exception e2) {
                                try {
                                    addDate = DateSafeUtils.parse(timeStr, "HH:mm");
                                    long addTime = addDate.getTime() - DateSafeUtils.parse("1970-01-01 00:00:00").getTime();
                                    date = new Date(date.getTime() + addTime);
                                } catch (Exception ignored) {
                                }
                            }
                        }
                        result =  DateSafeUtils.format(date);
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

    public static void main(String[] args) throws ParseException {
        Date parse = DateSafeUtils.parse("12:23:11", "HH:mm:ss");
        System.out.println(parse);
    }
}
