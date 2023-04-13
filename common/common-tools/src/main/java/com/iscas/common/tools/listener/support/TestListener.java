package com.iscas.common.tools.listener.support;

import com.iscas.common.tools.listener.CustomEventListener;
import com.iscas.common.tools.listener.CustomListener;
import com.iscas.common.tools.listener.event.TestEvent;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/7/31 17:40
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@CustomListener(name = "testListener", order = 10)
@Slf4j
public class TestListener implements CustomEventListener<TestEvent> {

    @Override
    public void handleEvent(TestEvent testEvent) {
        System.out.println(testEvent);
    }
}
