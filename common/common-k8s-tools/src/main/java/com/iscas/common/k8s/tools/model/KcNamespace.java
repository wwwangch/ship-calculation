package com.iscas.common.k8s.tools.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 命名空间信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 14:43
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcNamespace {

    /**
     * 命名空间的名字
     * */
    private String name;

    /**
     * API-VERSION，默认应该都为v1
     * */
    private String apiVersion = "v1";

    /**
     * 状态
     * */
    private String status;

    /**
     * 创建的时间
     * */
    private Date createTime;

    /**
     * 已运行的时间
     * */
    private String runTimeStr;
}
