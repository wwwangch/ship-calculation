package com.iscas.common.tools.thread;

import net.openhft.affinity.AffinityLock;
import org.junit.jupiter.api.Test;

/**
 * 测试线程亲和性调度
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/7/7 20:46
 * @since jdk1.8
 */
public class AffinityTests {

    @Test
    public void test() {
        try (AffinityLock affinityLock = AffinityLock.acquireLock(2)) {
            while (true) {

            }
        }

    }
}
