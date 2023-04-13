package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/25 16:09
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class DictData {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private long id;
    /**
     * 类型，系统类、业务类
     */
    private String dictType;
    /**
     * 枚举类型
     */
    private String dictDataType;
    /**
     * 字典标签
     */
    private String dictLabel;
    /**
     * 字典描述
     */
    private String dictDesc;
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

}
