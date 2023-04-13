package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.mapper.common.WsDataMapper;
import com.iscas.biz.model.common.WsData;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * websocket消息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/26 9:10
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
@Service
@Slf4j
@ConditionalOnMybatis
public class WsService {
    private WsDataMapper getWsDataMapper() {
        try {
            return SpringUtils.getBean(WsDataMapper.class);
        } catch (Exception e) {
            return null;
        }
    }

    private final SimpMessagingTemplate messagingTemplate;

//    private final WsDataMapper wsDataMapper;

    public WsService(SimpMessagingTemplate messagingTemplate/*, WsDataMapper wsDataMapper*/) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 广播消息
     * */
    public void broadCast(String destination, WsData data) throws BaseException {
        boolean persistent = data.isPersistent();
        if (persistent) {
            throw Exceptions.baseException("广播消息暂不支持持久化");
        }
        messagingTemplate.convertAndSend(destination, data);
    }

    /**
     * 点对点消息
     * */
    public void p2p(WsData wsData) {
        //如果需要持久化，存储
        if (wsData.isPersistent() && getWsDataMapper() != null) {
            storeToDb(wsData);
        }
        messagingTemplate.convertAndSendToUser(wsData.getUserIdentity(), wsData.getDestination(), wsData);
    }

    public void storeToDb(WsData wsData) {
        com.iscas.biz.domain.common.WsData dbWsData = com.iscas.biz.domain.common.WsData.convert(wsData);
        Objects.requireNonNull(getWsDataMapper()).insert(dbWsData);
    }

    public void retry(Integer id) {
        com.iscas.biz.domain.common.WsData wsData = Objects.requireNonNull(getWsDataMapper()).selectById(id);
        WsData wsData1 = com.iscas.biz.domain.common.WsData.convert(wsData);
        p2p(wsData1);
    }

    @Async("asyncExecutor")
    public void ack(String msgId) {
        if (getWsDataMapper() == null) {
            return;
        }
        List<com.iscas.biz.domain.common.WsData> wsDatas = getWsDataMapper().selectList(new QueryWrapper<com.iscas.biz.domain.common.WsData>()
                .lambda().eq(com.iscas.biz.domain.common.WsData::getMsgId, msgId));
        if (wsDatas != null) {
            for (com.iscas.biz.domain.common.WsData wsData : wsDatas) {
                wsData.setAck(true);
                try {
                    getWsDataMapper().updateById(wsData);
                } catch (Exception e) {
                    log.warn("消息:{}不存在", msgId);
//                    e.printStackTrace();
                }
            }
        }
    }
}
