package com.iscas.ssh.server.model;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.Data;

/**
 * SSH连接信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/27 14:07
 * @since jdk1.8
 */
@Data
public class SSHConnection {
//    private WebSocketSession webSocketSession;
    private String connectionId;
    private JSch jSch;
    private Channel channel;
    private long lastHeartbeatTime;
    private Session session;
}
