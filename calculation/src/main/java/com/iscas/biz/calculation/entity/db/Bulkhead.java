package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 13:57
 * 横舱壁-用于局部强度计算
 */
@Data
@TableName(value = "bulkhead",autoResultMap = true)
public class Bulkhead {
    @TableId(type = IdType.AUTO)
    private Integer bulkheadId;

    private Integer projectId;

    /**
     * 各层甲板高度文件名称
     */
    private String bulkheadFileName;

    /**
     * 各层甲板高度文件路径
     */
    private String bulkheadFilePath;
    /**
     * 是否防撞舱壁
     */
    private Boolean collisionBulkhead;

    private Date createTime;

    private Date updateTime;

    /**
     * 舱壁位置
     */
    private Double cangbiweizhi;
    /**
     * 舱壁肋位号
     */
    private Double leiweihao;
    /**
     * 水动压力
     */
    private Double shuidongyali;
    /**
     * 甲板纵骨跨距
     */
    private Double zonggukuaju;


    private Integer deckNum;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> deckNames;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> deckHeights;
}
