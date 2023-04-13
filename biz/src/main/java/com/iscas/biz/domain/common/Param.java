package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/26 9:44
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class Param {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 键
     */
    private String paramKey;

    /**
     * 值
     */
    private String paramValue;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 描述
     */
    private String paramDesc;


}
