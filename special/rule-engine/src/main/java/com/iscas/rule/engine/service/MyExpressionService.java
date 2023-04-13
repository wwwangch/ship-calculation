package com.iscas.rule.engine.service;

import com.iscas.rule.engine.anno.REComponent;
import com.iscas.rule.engine.model.Expression;
import com.iscas.rule.engine.model.Regulation;
import com.iscas.rule.engine.util.LogUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;

/**
 * 表达式处理业务
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 15:36
 * @since jdk1.8
 */
@REComponent
@Slf4j
@SuppressWarnings({"JavadocDeclaration", "rawtypes", "unchecked"})
public class MyExpressionService extends ExpressionService {

    @Override
    public void readExpression() {
        LogUtils.info(log, "正在读取规则");
        // TODO 这里应该从数据读取

        Expression expression = new Expression();
        expression.setExpressionLevel1("(1==1) && (math.abs(p1)>3.8 && p1<1000) && (p0 >=-100.0 && p0 <= 3000.0)");
        expression.setExpressionLevel2("(1==1) && (math.abs(p1)>3.8 && p1<1000) && ((p0 >=-100.0 && p0 <= 3000.0) && (((p0 >= -32.0 && p0 <= -3.0) || (p0 >= 500.0 && p0 <= 800.0)) ))");
        expression.setExpressionLevel3("(1==1) && (math.abs(p1)>3.8 && p1<1000) && ((p0 >=-100.0 && p0 <= 3000.0) && (((p0 >= -100.0 && p0 <= -32.0) || (p0 >= 800.0 && p0 <= 3000.0)) ))");
        expression.setAlarmCount(2);
        expression.setParamCode("M001CG091");
        Regulation regulation = new Regulation();
        regulation.setAlarmCount(2);

        expression.setRegulation(regulation);
        expression.setCheckInterval(600);
        LinkedHashMap map = new LinkedHashMap();
        map.put("p0", "M001CG091");
        map.put("p1", "M001CG092");
        expression.setParams(map);

        addExpression(expression);
        LogUtils.info(log, "读取规则结束");
    }
}
