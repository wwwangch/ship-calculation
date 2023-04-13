package com.iscas.rule.engine.service;

import com.iscas.rule.engine.anno.REComponent;
import com.iscas.rule.engine.model.DataBean;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * 队列service
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 16:27
 * @since jdk1.8
 */
@REComponent
@SuppressWarnings({"JavadocDeclaration", "AlibabaServiceOrDaoClassShouldEndWithImpl"})
public class MemoryQueueService implements IQueueService {
    private Queue<DataBean> queue = null;

    @Override
    public boolean offer(DataBean dataBean) {
        return queue.offer(dataBean);
    }

    @Override
    public DataBean pull() {
        return queue.poll();
    }

    @Override
    public void initQueues() {
        queue = new LinkedList<>();
        Random r = new Random();
        //TODO 模拟一堆数据
        for (int i = 0; i < 10; i++) {
            DataBean bean1 = new DataBean(r.nextInt(300) + 200.0, "M001CG091", "aaa", "abc", new Date());
            DataBean bean2 = new DataBean(r.nextInt(800) + 4, "M001CG092", "aaa", "abc", new Date());
            offer(bean1);
            offer(bean2);
        }

    }

}
