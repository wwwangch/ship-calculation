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
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote 附加压头计算
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

    //工况类型
    private CheckType checkType;

    /**
     * 肋位号数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> leiweihaos;

    /**
     * 附加压头数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> addyatous;

    /**
     * 甲板名称
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> strDecks;

    /**
     * 甲板破损压头水压值
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> deckYatous;


}
