package com.iscas.rule.engine.model;

import lombok.Data;

import java.util.List;

/**
 * 保存的规则
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/1/19 14:25
 * @since jdk1.8
 */
@Data
@SuppressWarnings("JavadocDeclaration")
public class Regulation {

    /**id*/
    private String id;

    /**规则类型，0-连续参数，1-状态参数*/
    private Integer type;

    /**分系统*/
    private String subsystem;

    /**所属单机*/
    private String machine;

    /**参数代号*/
    private String paramCode;

    /**参数名字*/
    private String paramName;

    /**单变量区间*/
    private SingleVariableRegion singleVariableRegion;

    /**多变量区间*/
    private List<MultiVariableRegion> multiVariableRegions;

    /**有效值*/
    private Threshold effective;

    /**门限配置*/
    private List<List<Threshold>> thresholds;

    /**正常范围*/
    private List<NormalVal> normalRegualations;

    /**报警次数*/
    private int alarmCount;

    /**是否生效0-生效 1 不生效*/
    private int regulationStatus;



}
