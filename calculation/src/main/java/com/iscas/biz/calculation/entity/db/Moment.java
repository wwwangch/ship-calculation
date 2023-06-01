package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/5/31 14:34
 * 中拱 中垂 弯矩
 */
@Data
public class Moment {
    @TableId(type = IdType.AUTO)
    private Integer momentId;

    private Integer projectId;

    private Integer sectionId;
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
}
