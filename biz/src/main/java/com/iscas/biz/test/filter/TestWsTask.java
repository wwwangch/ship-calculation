package com.iscas.biz.test.filter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.mapper.common.WsDataMapper;
import com.iscas.biz.model.common.WsData;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.service.common.WsService;
import com.iscas.templet.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("TestWsTask")
@Lazy(value = false)
@Slf4j
@ConditionalOnMybatis
public class TestWsTask {
    private final WsService wsService;
//    private final WsDataMapper wsDataMapper;

    private WsDataMapper getWsDataMapper() {
        try {
            return SpringUtils.getBean(WsDataMapper.class);
        } catch (Exception e) {
            return null;
        }
    }

    public TestWsTask(WsService wsService/*, WsDataMapper wsDataMapper*/) {
        this.wsService = wsService;
//        this.wsDataMapper = wsDataMapper;
    }

    /**
     * 定时任务
     * */
    public void test() throws BaseException {
        log.debug("======生成消息测试数据======");
        WsData wsData = new WsData(UUID.randomUUID().toString(), WsData.MsgTypeEnum.SYSTEM,
                "admin", false, "测试广播数据");
        wsService.broadCast("/topic/message", wsData);

        WsData wsData2 = new WsData(UUID.randomUUID().toString(), WsData.MsgTypeEnum.BUSINESS,
                "admin", false, "测试点对点数据，不做持久化" + System.currentTimeMillis());
        wsService.p2p(wsData2);

        //
//        WsDataExample wsDataExample = new WsDataExample();
//        wsDataExample.createCriteria().andAckEqualTo(false);
        if (getWsDataMapper() != null) {
            long l = getWsDataMapper().selectCount(new QueryWrapper<com.iscas.biz.domain.common.WsData>().lambda()
                    .eq(com.iscas.biz.domain.common.WsData::getAck, false));
            if (l < 200) {
                //如果库里有一些未回复的数据，就不再生成了，防止库爆了
                WsData wsData3 = new WsData(UUID.randomUUID().toString(), WsData.MsgTypeEnum.BUSINESS,
                        "admin", true, "测试点对点数据，持久化" + System.currentTimeMillis());
                wsService.p2p(wsData3);
            }
        } else {
            //如果库里有一些未回复的数据，就不再生成了，防止库爆了
            WsData wsData3 = new WsData(UUID.randomUUID().toString(), WsData.MsgTypeEnum.BUSINESS,
                    "admin", true, "测试点对点数据，持久化" + System.currentTimeMillis());
            wsService.p2p(wsData3);
        }


    }
}