package com.iscas.common.harbor.tools.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 镜像信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/8 21:20
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class Repository {
    /**project ID*/
    private Integer projectId;

    /**project name*/
    private String projectName;

    /**project是否公开*/
    private boolean projectPublic;

    /**下载的次数*/
    private Integer pullCount;

    /**
     * 镜像的名字
     * */
    private String name;

    /**
     * Tag数量
     * */
    private Integer tagsCount;

}
