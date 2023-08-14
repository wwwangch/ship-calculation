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
 * @since 2023/5/27 23:01
 * 砰击载荷
 */
@Data
@TableName(value = "slam_load",autoResultMap = true)
public class SlamLoad {
    @TableId(type = IdType.AUTO)
    private Integer slamLoadId;

    private Integer projectId;

    /**
     * 所属校验类型
     */
    private CheckType checkType;
    //航速
    private Double speed;
    //波峰抨击弯矩  数组  21个
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> pwbm;
    //波谷抨击弯矩  数组  21个
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> nwb;
}
