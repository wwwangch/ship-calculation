package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 *
 * CASE expression
 *     WHEN condition1 THEN result1
 *     WHEN condition2 THEN result2
 *    ...
 *     WHEN conditionN THEN resultN
 *     ELSE result
 * END
 *
 * CASE 表示函数开始，END 表示函数结束。如果 condition1 成立，则返回 result1, 如果 condition2 成立，则返回 result2，当全部不成立则返回 result，而当有一个成立之后，后面的就不执行了。
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/21 16:19
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class CASE_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持方法：CASE");
    }
}
