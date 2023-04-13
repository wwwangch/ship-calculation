package com.iscas.biz.config.log;

import com.iscas.biz.domain.common.LogInfo;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.service.common.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/2/21 18:16
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "AlibabaServiceOrDaoClassShouldEndWithImpl"})
@Service
@ConditionalOnMybatis
public class SotreLogService implements IStoreLogService {
    @Autowired
    private LogInfoService logInfoService;

    @Override
    public void store(LogInfo logInfo) {
        LogManager.executeLog(() -> logInfoService.save(logInfo));
    }
}
