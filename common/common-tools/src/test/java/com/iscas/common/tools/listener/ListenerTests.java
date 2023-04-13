package com.iscas.common.tools.listener;

import com.iscas.common.tools.listener.event.TestEvent;
import com.iscas.common.tools.listener.support.TestListener;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/1 9:26
 * @since jdk1.8
 */
public class ListenerTests {

    @Test
    public void testListener() {
        TestListener testListener = new TestListener();
        ListenerFactory.setListener(testListener);
        ListenerFactory.publish(new TestEvent("lalalalalala"));
    }
}
