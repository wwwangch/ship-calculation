package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * LAST_INSERT_ID() 	返回最近生成的 AUTO_INCREMENT 值
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/25 16:15
 * @since jdk11
 */
@SuppressWarnings("unused")
public class LAST_INSERT_ID_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持函数:LAST_INSERT_ID");
    }
}
