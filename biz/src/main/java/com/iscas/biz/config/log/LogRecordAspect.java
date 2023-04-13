package com.iscas.biz.config.log;


import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.config.log.LogRecordConfig;
import com.iscas.base.biz.util.JWTUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.domain.common.LogInfo;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.templet.exception.AuthenticationRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * aop
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/02/21
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Aspect
@Component
@Slf4j
public class LogRecordAspect implements Constants {

    @Around("@annotation(logRecord)")
    public Object around(final ProceedingJoinPoint joinPoint, LogRecord logRecord) throws Throwable {
        //如果未开启日志注解，不进行逻辑
        if (!LogRecordConfig.flag) {
            return joinPoint.proceed();
        }
        return handle(joinPoint, logRecord);
    }

    private Object handle(ProceedingJoinPoint joinPoint, LogRecord logRecord) throws Throwable {
        StringBuilder desc = new StringBuilder();
        IStoreLogService storeLog = SpringUtils.getApplicationContext().getBean(IStoreLogService.class);
        LogInfo logInfo = new LogInfo();
        //从token里取出userName
        String username;
        try{
            username = JWTUtils.getLoginUsername();
        }catch (AuthenticationRuntimeException e){
            username = "unknown";
        }

        HttpServletRequest request = SpringUtils.getRequest();
        logInfo.setLogType(logRecord.type().getValue())
                .setOperateType(logRecord.operateType().getValue())
                .setOperateTime(DateSafeUtils.format(new Date()))
                .setOperateUser(StringUtils.isNotBlank(username) ? username : "unknown")
                .setRequestUrl(request.getRequestURI());

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            desc.append("操作成功:");
            return result;
        } catch (Exception e) {
            log.error("请求出错, error：{}", e.getMessage());
            desc.append("操作失败:");
            throw e;
        } finally {
            try {
                logInfo.setLogDesc(desc.append(logRecord.desc()).toString()).setRequestTookTime(System.currentTimeMillis() - start);
                storeLog.store(logInfo);
            } catch (Throwable e) {
                log.error("记录日志出错, error：{}", e.getMessage());
            }
        }
    }
}
