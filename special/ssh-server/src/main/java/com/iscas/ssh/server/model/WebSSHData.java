package com.iscas.ssh.server.model;

import lombok.Data;

/**
 * SSH信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/27 14:09
 * @since jdk1.8
 */
@Data
public class WebSSHData {
    //操作
    private String operate;
    private String host;
    //端口号默认为22
    private Integer port = 22;
    private String username;
    private String password;
    private String command = "";

    private String connectionId;

    private TerminalSize size;

}
