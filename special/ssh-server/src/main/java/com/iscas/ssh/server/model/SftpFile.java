package com.iscas.ssh.server.model;

import lombok.Data;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/3 17:18
 * @since jdk1.8
 */
@Data
public class SftpFile {
    /**
     * 文件名称
     * */
    private String name;

    /**
     * 文件大小(KB)
     * */
    private long size;

    /**
     * 是否为文件
     * */
    private boolean isFile;

    /**
     * 文件路径
     * */
    private String path;
}
