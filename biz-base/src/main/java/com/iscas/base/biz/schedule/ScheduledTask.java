package com.iscas.base.biz.schedule;

import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务控制类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/2/25 18:18
 * @since jdk1.8
 */
public final class ScheduledTask {

    public volatile ScheduledFuture<?> future;
    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
