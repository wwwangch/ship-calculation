package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.iscas.biz.calculation.entity.dto.Buoyant;
import lombok.Data;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/25 17:02
 */
@Data
@TableName(value = "buoyancy_result",autoResultMap = true)
public class BuoyancyResult {
    @TableId(type = IdType.AUTO)
    private Integer resultId;

    private Integer paramId;

    //校核b(x)
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> blist;

    //每次校核计算的结果
    @TableField(typeHandler =JacksonTypeHandler.class)
    private List<Buoyant> calrst;
}
