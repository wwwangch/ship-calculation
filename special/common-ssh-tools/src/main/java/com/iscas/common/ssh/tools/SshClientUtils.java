package com.iscas.common.ssh.tools;

import com.iscas.common.ssh.tools.model.SshClientDto;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.*;

/**
 * SSH窗口工具
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/9 17:02
 * @since jdk1.8
 */
public class SshClientUtils {
    private SshClientUtils() {}

    public static SshClientDto openSshSession(String host, int port, String username, String password, int timeout) throws JSchException, IOException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        try {
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(timeout);
            session.connect();
            Channel exec = session.openChannel("shell");

            InputStream is = exec.getInputStream();
            OutputStream os = exec.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            exec.connect(5000);
            SshClientDto sshClientDto = new SshClientDto(session, pw, br, is, os);
            return sshClientDto;
        } catch (Exception e) {
            if (session != null) session.disconnect();
            throw e;
        }
    }

}
