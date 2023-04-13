package com.iscas.biz.test.service.sharding;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.biz.test.mapper.TOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/28 21:13
 * @since jdk1.8
 */
@Service
@ConditionalOnMybatis
public class TOrderService {
    @Autowired
    private DynamicMapper dynamicMapper;
    @Autowired
    private TOrderMapper orderMapper;

    public void insert() {
        //mybatis-plus
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < 100; i++) {
            TOrder order = new TOrder();
            order.setOrderId(i + 1);
            order.setUserId(threadLocalRandom.nextInt(200));
            order.setOrderName("order" + (i + 1));
            order.setOrderDesc("order desc" + (i + 1));
            orderMapper.insert(order);
        }


        //直接写SQL
        for (int i = 100; i < 200; i++) {
            String sql = String.format("insert into t_order(order_id, user_id, order_name, order_desc) values (%d, %d, 'order%d', 'order desc%d')",
                    i + 1, threadLocalRandom.nextInt(200), i + 1, i + 1);
            dynamicMapper.insertBySql(sql);
        }

    }
}
