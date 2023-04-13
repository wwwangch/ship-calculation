package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.arithmetic.IntegerDivision;

import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 16:21
 * @since jdk11
 */
@SuppressWarnings("JavadocDeclaration")
public class DivHandler implements FunctionHandler {
    public void handle(Map<String, Object> data, Alias alias, IntegerDivision integerDivision) {
        Expression leftExpression = integerDivision.getLeftExpression();
        Expression rightExpression = integerDivision.getRightExpression();
        Object first = getData(data, leftExpression);
        Object second = getData(data, rightExpression);
        Object result = null;
        if (first != null && second != null) {
            double n = 0,m = 0;
            try {
                n = Double.parseDouble(first.toString());
                m = Double.parseDouble(second.toString());
            } catch (Exception ignored) {}
            if (m != 0) {
                result = n / m;
            }
        }
        if (alias != null) {
            data.put(alias.getName(), result);
        } else {
            data.put(integerDivision.toString(), result);
        }
    }

    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {

    }
}
