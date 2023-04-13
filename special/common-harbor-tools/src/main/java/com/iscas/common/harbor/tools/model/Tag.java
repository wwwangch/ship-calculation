package com.iscas.common.harbor.tools.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/9 10:16
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class Tag {
    /**摘要*/
    private String digest;
    /**名称*/
    private String name;
    /**大小Byte*/
    private long size;
    /**体系架构*/
    private String architecture;
    /**操作系统*/
    private String os;
    /**系统版本*/
    private String osVersion;
    /**docker版本*/
    private String dockerVersion;
    /**创建时间*/
    private Date createTime;
    /**推送时间*/
    private Date pushTime;
    /**拉取时间*/
    private Date pullTime;

}
