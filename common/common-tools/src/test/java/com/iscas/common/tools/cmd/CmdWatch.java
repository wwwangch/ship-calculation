package com.iscas.common.tools.cmd;

import org.apache.commons.exec.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/24 19:16
 * @since jdk1.8
 */
public class CmdWatch {

    //在使用Runtime.getRuntime().exec(cmd)执行某些系统命令，如nfs共享的mount时，
    // 会由于nfs服务异常等原因导致进程阻塞，使程序没法往下执行，而且也无法捕获到异常，相当于卡死在了。
    // 这时如果有超时放弃的功能就好了，当然超时功能可以自己轮询process.exitValue()去实现，但麻烦一些。
    // commons-exec主要通过ExecuteWatchdog类来处理超时，下面看例子
    @Test
    public void testCommonExecWatch() throws IOException {
        String command = "ping localhost";
        ByteArrayOutputStream susStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        CommandLine commandLine = CommandLine.parse(command);
        DefaultExecutor exec = new DefaultExecutor();
        //设置一分钟超时
        ExecuteWatchdog watchdog = new ExecuteWatchdog(60 * 1000);
        exec.setWatchdog(watchdog);
        PumpStreamHandler streamHandler = new PumpStreamHandler(susStream, errStream);
        exec.setStreamHandler(streamHandler);
        try {
            int code = exec.execute(commandLine);
            System.out.println("result code: " + code);
            // 不同操作系统注意编码，否则结果乱码
            String suc = susStream.toString("GBK");
            String err = errStream.toString("GBK");
            System.out.println(suc + err);
        } catch (ExecuteException e) {
            if (watchdog.killedProcess()) {
                // 被watchdog故意杀死
                System.err.println("超时了");
            }
        }
    }
}
