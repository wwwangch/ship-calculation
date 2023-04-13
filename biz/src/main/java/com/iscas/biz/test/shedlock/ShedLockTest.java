package com.iscas.biz.test.shedlock;

import com.iscas.base.biz.config.shedlock.ConditionalOnShedLock;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/20 20:39
 * @since jdk1.8
 */
@Component
@Slf4j
@Lazy(false)
@EnableScheduling
@ConditionalOnShedLock
public class ShedLockTest {
    /**
     * 每隔一小时执行
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    // @Scheduled(cron = "0 0 17 * * ?") 每天17点执行
    @Scheduled(cron = "0 0 */1 * * ?")
    @SchedulerLock(name = "shedLockTest", lockAtMostFor = "2m", lockAtLeastFor = "10s")
    public void SynchronousSchedule() {
        log.info("测试定时任务锁，使用spring的Scheduled");
    }
}
