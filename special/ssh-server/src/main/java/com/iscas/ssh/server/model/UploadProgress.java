package com.iscas.ssh.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/3 17:54
 * @since jdk1.8
 */
@Data
@AllArgsConstructor
public class UploadProgress {
    private String id;
    private int total;
    private int current;

}
