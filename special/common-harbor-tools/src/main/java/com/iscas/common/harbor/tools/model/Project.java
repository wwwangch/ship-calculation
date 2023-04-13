package com.iscas.common.harbor.tools.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 项目
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/8 21:39
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class Project {
    /**ID*/
    private Integer projectId;

    /**拥有者的ID*/
    private Integer ownerId;

    /**
     * 名称
     * */
    private String name;

    /**
     * 创建时间
     * */
    private Date createTime;

    /**最后修改时间*/
    private Date updateTime;

    /**是否已经删除*/
    private Boolean deleted;

    /**拥有的镜像仓库的数量*/
    private Integer repoCount;

    /**是否公开*/
    private Boolean projectPublic;

}
