package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.iscas.biz.calculation.enums.CheckType;
import lombok.Data;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/27 22:36
 */
@Data
@TableName(value = "static_load",autoResultMap = true)
public class StaticLoad {
    @TableId(type = IdType.AUTO)
    private Integer staticLoadId;

    private Integer projectId;
    /**
     * 所属校验类型
     */
    private CheckType checkType;


    //未修正的静水剪力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> nvec;

    //未修正的弯矩
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> mvec;

    //修正的静水剪力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> nvecM;

    //修正的弯矩
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> mvecM;
}
