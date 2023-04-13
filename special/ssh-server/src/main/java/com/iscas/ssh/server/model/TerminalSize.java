package com.iscas.ssh.server.model;

import lombok.Data;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/30 14:27
 * @since jdk1.8
 */
@Data
public class TerminalSize {
    private int cols;
    private int rows;
    private int height;
    private int width;
}
