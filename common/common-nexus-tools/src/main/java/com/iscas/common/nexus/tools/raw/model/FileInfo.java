package com.iscas.common.nexus.tools.raw.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/19 9:18
 */
@Data
public class FileInfo {

    /**
     * ID
     * */
    private String id;

    /**
     * 文件名称
     * */
    private String name;

    /**
     * 文件类型 file / dir
     * */
    private String type;

    /**
     * 文件路径
     * */
    private String path;

    /**
     * 子文件
     * */
//    private List<FileInfo> children;

    /**
     * 最后修改时间
     * */
    private LocalDateTime lastModified;

    /**
     * 文件大小
     * */
    private long size;

    /**
     * 描述
     * */
    private String description;

}
