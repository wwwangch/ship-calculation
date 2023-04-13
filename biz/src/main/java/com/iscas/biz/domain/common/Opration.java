package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Opration {

    @TableId(type = IdType.AUTO)
    private Integer opId;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String opName;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date opCreateTime;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date opUpdateTime;

}