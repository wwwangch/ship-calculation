package com.iscas.common.ssh.tools;

import com.iscas.common.ssh.tools.model.SshClientDto;
import com.jcraft.jsch.JSchException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/9 17:58
 * @since jdk1.8
 */
public class Test3 {
    public static void main(String[] args) throws IOException, JSchException, InterruptedException {
        SshClientDto sshClientDto = SshClientUtils.openSshSession("localhost", 22, "zqw02", "123456", 6000);
        TimeUnit.SECONDS.sleep(3);
        BufferedReader br = sshClientDto.getBr();
        new Thread(() -> {
            String line = null;
            while (true) {
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(line);
            }
        }).start();

        PrintWriter pw = sshClientDto.getPw();

        pw.println("date");
        pw.flush();
        //输入contrl + c 断掉正在执行的任务
        pw.write(3);
        pw.println();
        pw.flush();
        //命令
        pw.println("date");
        pw.flush();

//        pw.write(3);
//        pw.flush();
//
//        pw.write(3);
//        pw.println();
//        pw.flush();

    }
}
