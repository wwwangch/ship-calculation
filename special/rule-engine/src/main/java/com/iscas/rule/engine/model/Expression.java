package com.iscas.rule.engine.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @version 1.02
 * @date 2020/1/20 10:02
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@SuppressWarnings({"JavadocDeclaration", "rawtypes"})
public class Expression implements Comparable {
    /**
     * 规则
     * */
    private Regulation regulation;

    /**
     * 正常范围值判断，状态型
     * */
    private String expressionLevel0;

    /**
     * 表达式重度
     * */
    private String expressionLevel1;

    /**
     * 表达式中度
     * */
    private String expressionLevel2;

    /**
     * 表达式重度
     * */
    private String expressionLevel3;

    /**
     * 包ID
     * */
    private String packageId;

    /**
     * 卫星ID
     * */
    private String sateId;

    /**
     * 卫星名字
     * */
    private String sateName;

    /**
     * 分系统
     * */
    private String subsystem;

    /**
     * 所属单机
     * */
    private String device;

    /**
     * 参数代号
     * */
    private String paramCode;

    /**
     * 参数名字
     * */
    private String paramName;

    /**
     * 连续报警次数
     * */
    private int alarmCount;

    /**
     * 报警后间隔多少S再次做检测(单位秒)
     * */
    private int checkInterval = 0;

    /**
     * 参数 key 表达式内的参数，value 参数代号
     * */
    private LinkedHashMap<String, String> params = new LinkedHashMap<>();

    /**
     * 是否为知识流程编辑
     * */
    private boolean flowFlag = false;

    /**
     * 知识流程表达式
     * */
    private List<FlowExpression> flowExpresssions = new ArrayList<>();


    @Override
    public int compareTo(@NotNull Object o) {
        Expression expression = (Expression) o;
        return this.toString().compareTo(expression.toString());
    }
}
