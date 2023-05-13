package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.iscas.biz.calculation.enums.CheckType;
import com.iscas.biz.calculation.enums.NavigationArea;
import com.iscas.biz.calculation.enums.ShipType;
import lombok.Data;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 15:40
 * 船舶参数
 */
@Data
public class ShipParam {
    @TableId(type = IdType.AUTO)
    private Integer paramId;

    private Integer projectId;

    /**
     * 船类型 ZQJ HM
     */
    private ShipType shipType;

    /**
     * 设计水线长(m)
     */
    private Double waterlineLength;

    /**
     * 型宽(m)
     */
    private Double mouldedBreadth;

    /**
     * 型深(m)
     */
    private Double mouldedDepth;

    /**
     * 设计吃水(m)
     */
    private Double designedDraft;

    /**
     * 站数
     */
    private Integer station;

    /**
     * 航区 有限和无限
     */
    private NavigationArea navigationArea;

    private CheckType checkType;

    /**
     * 排水量
     */

    private Double displacement;

    /**
     * 重心纵向位置
     */
    private Double portraitGravity;

    private Date createTime;

    private Date updateTime;
}
