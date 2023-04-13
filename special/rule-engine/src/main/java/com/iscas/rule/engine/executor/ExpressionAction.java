package com.iscas.rule.engine.executor;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/1/20 10:54
 * @since jdk1.8
 */
@SuppressWarnings({"JavadocDeclaration", "unused"})
public class ExpressionAction {
    private ExpressionAction() {
    }

    public static boolean action(String expression, Map<String, Object> env) {
        Expression compiledExp2 = AviatorEvaluator.compile(expression);
        // 执行表达式
        return (Boolean) compiledExp2.execute(env);
    }

}
