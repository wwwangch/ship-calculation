package com.iscas.common.tools.disruptor.simple;

import com.lmax.disruptor.WorkHandler;
import lombok.AllArgsConstructor;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/18 15:09
 * @since jdk1.8
 */
@AllArgsConstructor
public class MyWorkHandler implements WorkHandler {
    private String name;
    @Override
    public void onEvent(Object event) throws Exception {
        System.out.println(name + "-> 接收到消息：" + event);
    }
}
