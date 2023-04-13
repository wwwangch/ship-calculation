package com.iscas.rule.engine.service;

import com.iscas.rule.engine.model.DataBean;

/**
 * 队列service接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 16:27
 * @since jdk1.8
 */
@SuppressWarnings({"JavadocDeclaration", "UnusedReturnValue"})
public interface IQueueService {
    /**
     * 入队
     *
     * @param dataBean 实体
     * @return boolean
     * @date
     * @since jdk11
     */
    boolean offer(DataBean dataBean);

    /**
     * 出队
     *
     * @return com.iscas.rule.engine.model.DataBean
     * @date
     * @since jdk11
     */
    DataBean pull();

    /**
     * 初始化队列
     *
     * @date 2022/6/26
     * @since jdk11
     */
    void initQueues();
}
