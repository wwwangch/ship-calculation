package com.iscas.biz.calculation.entity.dto.sigma;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.List;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/6/1 10:14
 */
@Data
public class Sigma1DTO {
    private Integer projectId;
    private Integer sectionId;
    /**
     *
     * 原先为：剖面构件跨距a  componentSpan
     * 修改为：构件跨距-龙骨跨距 每个的跨距
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> kuaChang;

    /**
     * 原先为：校核剖面位置X xCoordinate
     * 修改为：剖面位置x-横梁间距  girderDistance
     */
    private Double girderDistance;

    /**
     * 纵骨间距
     */
    private Double frDistance;
    /**
     * 板架宽度
     */
    private Double trusswidth;

    /**
     * 纵骨规格 每个的规格
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> frGuige;
    /**
     *板格厚度 每个板材的厚度
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> plateThick;
    /**
     * 设备重量
     */
    private Double deviceWeight;
    /**
     * 板格宽度
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> girderWidth;
    /**
     * 材料类型  改为材料屈服极限
     */
    private Double materialYieldLimit;
    /**
     * 是否采用自定义载荷
     */
    private Boolean isCustomLoad;

    /**
     * 中拱-波浪弯矩
     */
    private Double midArchWaveMoment;
    /**
     * 中拱-砰击振动弯矩
     */
    private Double midArchImpactMoment;
    /**
     * 中拱-剪力
     */
    private Double midArchShear;
    /**
     * 中拱-吃水
     */
    private Double midArchDraught;
    /**
     * 中垂-波浪弯矩
     */
    private Double midVerticalWaveMoment;
    /**
     * 中垂-砰击振动弯矩
     */
    private Double midVerticalImpactMoment;
    /**
     * 中垂-剪力
     */
    private Double midVerticalShear;
    /**
     * 中垂-吃水
     */
    private Double midVerticalDraught;
    /**
     * 舷侧中和轴附近板的厚度
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> xiancethick;


    /**
     * 龙骨数量
     */
    private Integer numGirders;
}
