package com.iscas.base.biz.model.access;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 访问日志的实体
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/29 20:37
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class AccessLog {

    /**访问用户*/
    private String username = "unknown";

    /**uri*/
    private String uri;

    /**访问时长*/
    private Long duration;

    /**请求方式*/
    private String method;

    /**客户端IP*/
    private String ip;

    /**状态码*/
    private int status;

    /**时间*/
    private Date createTime;

}
