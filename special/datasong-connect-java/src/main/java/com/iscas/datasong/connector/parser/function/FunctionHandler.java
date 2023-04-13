package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.util.ArrayUtil;
import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.schema.Column;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * <a href="https://www.runoob.com/mysql/mysql-functions.html">https://www.runoob.com/mysql/mysql-functions.html</a>
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/28 10:40
 * @since jdk11
 */
@SuppressWarnings("JavadocDeclaration")
public interface FunctionHandler {
    /**
     * 处理函数
     *
     * @param data  数据
     * @param alias 别名
     * @param func  function
     */
    void handle(Map<String, Object> data, Alias alias, Function func);

    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    default boolean isStr(String columnName) {
        return (columnName.startsWith("\"") && columnName.endsWith("\"")) ||
                (columnName.startsWith("'") && columnName.endsWith("'"));
    }

    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    default Object getData(Map<String, Object> data, Expression expression) {
        if (expression instanceof Column) {
            Column column = (Column) expression;
            String columnName = column.getColumnName();
            if (isStr(columnName)) {
                columnName = columnName.substring(1, columnName.length() - 1);
                return columnName;
            } else {
                return data.getOrDefault(columnName, null);
            }
        } else if (expression instanceof StringValue) {
            return ((StringValue) expression).getValue();
        } else if (expression instanceof LongValue) {
            return ((LongValue) expression).getValue();
        } else if (expression instanceof DoubleValue) {
            return ((DoubleValue) expression).getValue();
        } else if (expression instanceof SignedExpression) {
            SignedExpression signedExpression = (SignedExpression) expression;
            char sign = signedExpression.getSign();
            if (sign == '-') {
                Object data1 = getData(data, ((SignedExpression) expression).getExpression());
                if (data1 instanceof Long) {
                    Long d = (Long) data1;
                    return -d;
                } else if (data1 instanceof Double) {
                    Double d = (Double) data1;
                    return -d;
                } else {
                    throw new RuntimeException(String.format("不支持的Expression类型:[%s]", expression.getClass().getName()));
                }
            } else {
                throw new RuntimeException(String.format("不支持的Expression类型:[%s]", expression.getClass().getName()));
            }
        } else if (expression instanceof IntervalExpression) {
            return expression;
        } else if (expression instanceof NullValue) {
            return null;
        } else {
            throw new RuntimeException(String.format("不支持的Expression类型:[%s]", expression.getClass().getName()));
        }
    }

    default String escape(String str) {
        String[] needStrs = new String[]{"$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|", "\\"};
        if (ArrayUtil.contains(needStrs, str)) {
            return "\\" + str;
        }
        return str;
    }

    default long getIntervalMs(Date date, IntervalExpression intervalExpression, boolean add) {
        long res = 0L;
        String parameter = intervalExpression.getParameter();
        String intervalType = intervalExpression.getIntervalType();
        try {
            double v = Double.parseDouble(parameter);
            long x = Math.round(v);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            switch (intervalType.toUpperCase(Locale.ROOT)) {
                case "DAY":
                    if (add) {
                        res = date.getTime() + x * 24 * 3600 * 1000L;
                    } else {
                        res = date.getTime() - x * 24 * 3600 * 1000L;
                    }
                    break;
                case "MONTH":
                    c.add(Calendar.MONTH, add ? (int) x : -1 * (int) x);
                    break;
                case "HOUR":
                    c.add(Calendar.HOUR, add ? (int) x : -1 * (int) x);
                    break;
                case "MINUTE":
                    c.add(Calendar.MINUTE, add ? (int) x : -1 * (int) x);
                    break;
                case "SECOND":
                    c.add(Calendar.SECOND, add ? (int) x : -1 * (int) x);
                    break;
                case "MILLISECOND":
                    c.add(Calendar.MILLISECOND, add ? (int) x : -1 * (int) x);
                    break;
                case "WEEK":
                    if (add) {
                        res = date.getTime() + x * 7 * 24 * 3600 * 1000L;
                    } else {
                        res = date.getTime() - x * 7 * 24 * 3600 * 1000L;
                    }
                    break;
                case "YEAR":
                    c.add(Calendar.YEAR, add ? (int) x : -1 * (int) x);
                    break;
                default: throw new RuntimeException(String.format("不支持表达式：" + intervalExpression));
            }
            res = c.getTime().getTime();
        } catch (Exception ignored) {}
        return res;
    }

    default Date getDate(Object obj) {
        Date date = null;
        if (obj instanceof Date) {
            date = (Date) obj;
        } else {
            try {
                date = DateSafeUtils.parseStringToDate(obj.toString());
            } catch (ParseException ignored) {
            }
        }
        return date;
    }

    default Date getTime(Object obj) {
        Date date;
        if (obj instanceof Date) {
            date = (Date) obj;
        } else {
            try {
                date = DateSafeUtils.parse("2020-01-01 " + obj.toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return date;
    }

    default String getWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
            default:
                return null;
        }
    }

    default String getMonth(int month) {
        switch (month) {
            case Calendar.JANUARY:
                return "January";
            case Calendar.FEBRUARY:
                return "February";
            case Calendar.MARCH:
                return "March";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "May";
            case Calendar.JUNE:
                return "June";
            case Calendar.JULY:
                return "July";
            case Calendar.AUGUST:
                return "August";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "October";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "December";
            default:
                return null;
        }
    }

}
