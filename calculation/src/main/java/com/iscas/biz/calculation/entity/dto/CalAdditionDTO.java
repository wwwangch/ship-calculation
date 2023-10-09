package com.iscas.biz.calculation.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */

@Data
public class CalAdditionDTO {

    private Integer projectId;

    private Integer bulkheadId;

    /**
     * 上建形式
     */
    private Integer upBuiltForm;

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

    //正常排水量时的吃水
    private Double draugthnoraml;
    //是否防撞舱壁
    private Boolean collisionBulkhead;

    /**
     * 水动压力
     */
    private Double shuidongyali;

    /**
     * 舱壁肋位号
     */
    private Double leiweihao;

    /**
     * 液舱空气管压头
     */
    private Double airguanyatou;

    private List<String> deckName;

    private List<Number> deckHeight;

    private List<Boolean> liquidTanks;
}
