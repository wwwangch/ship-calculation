package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.iscas.biz.calculation.enums.CheckType;
import com.iscas.biz.calculation.enums.NavigationArea;
import com.iscas.biz.calculation.enums.ShipType;
import com.iscas.biz.calculation.enums.UpBuiltForm;
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
    @EnumValue
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

//    /**
//     * 校验工况(已经弃用,通规不需要这个类型，其他两个规范同时有两个核校工况)
//     */
//    private CheckType checkType;

    /**
     * 排水量(默认是极限)
     */

    private Double displacement;

    /**
     * 重心纵向位置(默认是极限)
     */
    private Double portraitGravity;

    //========new 8.3=============
    /**
     * 排水量(此处是巡航工况)
     */

    private Double cruisingDisplacement;

    /**
     * 重心纵向位置(此处是巡航工况)
     */
    private Double cruisingPortraitGravity;

    /**
     * 当前校验工况
     */
    private CheckType currentType;

    //静水中最大航速
    private Double vmax;

    //航速
    private Double speed;

    //========new 8.3=============

    /**
     * 液舱空气管压头
     */
    private Double airguanyatou;

    /**
     * 上建形式
     */
    private UpBuiltForm upBuiltForm;

    /**
     * 干舷高
     */
    private Double freeboard;

    /**
     * 船中肋位号
     */
    private Double midRibNumber;

    /**
     * 肋骨间距
     */
    private Double ribSpacing;

    /**
     * 端壁肋位号
     */
    private Double ribNumber;

    /**
     * 首干舷高
     */
    private Double foreFreeBoard;

    /**
     * 尾干舷高
     */
    private Double afterFreeBoard;
    /**
     * 桥楼首部端壁肋位号
     */
    private Double bridgeForePos;
    //桥楼首部端壁干舷高
    private Double bridgeForeHeight;
    //桥楼尾部端壁肋位号；
    private Double bridgeAftPos;
    //桥楼尾部端壁干舷高
    private Double bridgeAftHeight;

    private Date createTime;

    private Date updateTime;
}
