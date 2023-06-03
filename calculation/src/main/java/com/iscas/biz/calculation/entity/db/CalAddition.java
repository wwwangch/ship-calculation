package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.List;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */

@Data
@TableName(value = "cal_addition", autoResultMap = true)
public class CalAddition {

    @TableId(type = IdType.AUTO)
    private Integer calAdditionId;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 舱壁ID
     */
    private Integer bulkheadId;

    /**
     * 干舷高
     */
    private Double freeboard;

    /**
     *舱壁肋位号
     */
    private Double leiweihao;

    /**
     *舱壁位置
     */
    private Double cangbiWeizhi;

    /**
     *是否防撞舱壁
     */
    private Boolean isCollision;

    /**
     *水动压力
     */
    private Double shuidongYali;

    /**
     *肋位号数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> leiweihaos;

    /**
     *附加压头数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> addyatouh;




}
