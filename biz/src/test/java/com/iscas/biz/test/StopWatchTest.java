package com.iscas.biz.test;

import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/7 13:41
 * @since jdk1.8
 */
public class StopWatchTest {
    @Test
    public void test() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("测试");
        stopWatch.start("测试1");
        TimeUnit.SECONDS.sleep(1);
        stopWatch.stop();
        stopWatch.start("测试2");
        TimeUnit.SECONDS.sleep(2);
        stopWatch.stop();
        stopWatch.start("测试3");
        TimeUnit.SECONDS.sleep(3);
        stopWatch.stop();

        // 获取某一个任务的耗时
        StopWatch.TaskInfo[] taskInfo = stopWatch.getTaskInfo();
        Arrays.stream(taskInfo).forEach(ti -> {
            System.out.println(ti.getTimeMillis());
        });

        // 获取总体耗时
        System.out.println(stopWatch.getTotalTimeMillis());

        // 获取上一个任务的耗时
        System.out.println(stopWatch.getLastTaskTimeMillis());

        // 格式化输出
        System.out.println(stopWatch.prettyPrint());

        // 查看是否在运行中
        System.out.println(stopWatch.isRunning());


    }
}
