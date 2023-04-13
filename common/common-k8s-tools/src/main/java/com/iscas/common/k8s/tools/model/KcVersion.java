package com.iscas.common.k8s.tools.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * kubernetes 版本号详情
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 14:28
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcVersion {
    /**
     * git的版本号，一般使用它作为对外的版本号
     * */
    private String gitVersion;

    /**
     * 支持的平台
     * */
    private String platform;

    /**
     * 编译者
     * */
    private String compiler;


    /**
     * 使用的go语言版本
     * */
    private String goVersion;

    /**
     * git提交的ID
     * */
    private String gitCommit;

    /**
     * 编译的时间
     * */
    private Date buildDate;
}
