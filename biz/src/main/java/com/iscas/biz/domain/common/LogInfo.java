package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/20 17:09
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class LogInfo {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private long id;
    /**
     * 日志类型
     */
    private String logType;
    /**
     * 请求地址
     */
    private String requestUrl;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 操作用户
     */
    private String operateUser;
    /**
     * 描述
     */
    private String logDesc;
    /**
     * 操作时间
     */
    private String operateTime;
    /**
     * 响应时间
     */
    private long requestTookTime;
}
