package com.iscas.base.biz.test.schedule;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 编写一个任务类和执行函数
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/2/25 18:31
 * @since jdk1.8
 */
@Component("TestTask")
@Lazy(value = false)
public class TestTask {
    /**
     * 定时任务
     * */
    public void test() {
        System.out.println(1111);
    }
}
