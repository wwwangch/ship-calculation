package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * CONNECTION_ID() 返回唯一的连接 ID
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/25 8:51
 * @since jdk11
 */
@SuppressWarnings("unused")
public class CONNECTION_ID_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持函数:CONNECTION_ID");
    }
}
