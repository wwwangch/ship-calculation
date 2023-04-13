package com.iscas.common.ssh.tools;

import com.iscas.common.ssh.tools.model.SshClientDto;
import com.jcraft.jsch.JSchException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * SSH客户端测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/9 17:15
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class SshClientUtilsTests {

    @Test
    public void test() throws IOException, JSchException {
        SshClientDto sshClientDto = SshClientUtils.openSshSession("192.168.100.96", 22, "root", "root", 6000);
        BufferedReader br = sshClientDto.getBr();
        new Thread(() -> {
            String line = null;
            while (true) {
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(line);
            }
        }).start();

        PrintWriter pw = sshClientDto.getPw();
        pw.println();
        pw.flush();

        pw.write(3);
        pw.flush();

        pw.write(3);
        pw.flush();

        pw.write(3);
        pw.flush();


    }
}
