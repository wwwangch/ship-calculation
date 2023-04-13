package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/1 15:13
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class DictDataType {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private long id;
    /**
     * 枚举类型
     */
    private String dictDataType;
    /**
     * 键
     */
    private String dictDataKey;
    /**
     * 值
     */
    private String dictDataValue;
    /**
     * 字典描述
     */
    private String dictDataDesc;
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
