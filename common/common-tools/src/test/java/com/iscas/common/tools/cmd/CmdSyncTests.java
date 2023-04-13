package com.iscas.common.tools.cmd;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 同步调用测试
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/24 17:41
 * @since jdk1.8
 */
public class CmdSyncTests {

    @Test
    public void testJdk() throws InterruptedException, IOException {
        final Process process = Runtime.getRuntime().exec("cmd /c ping localhost");
        new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        process.exitValue();
                        break; // exitValue没有异常表示进程执行完成，退出循环
                    } catch (IllegalThreadStateException e) {
                        // 异常代表进程没有执行完
                    }
                    //此处只做输出，对结果有其他需求可以在主线程使用其他容器收集此输出
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        process.waitFor();
    }


    //commons-exec的command不需要考虑执行环境了，比如windows下不需要添加"cmd /c "的前缀。
    // 可以使用自定义的流来接受结果，比如使用文件流将结果保存到文件，使用网络流保存到远程服务器上等。
    // 下面的例子为了简单直接使用字节流去接收（如果结果非常大就不要用字节流了，容易内容溢出）。
    @Test
    public void testCommonExec() throws IOException {
        String command = "ping localhost";
        //接收正常结果流
        ByteArrayOutputStream susStream = new ByteArrayOutputStream();
        //接收异常结果流
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        CommandLine commandLine = CommandLine.parse(command);
        DefaultExecutor exec = new DefaultExecutor();
        PumpStreamHandler streamHandler = new PumpStreamHandler(susStream, errStream);
        exec.setStreamHandler(streamHandler);
        int code = exec.execute(commandLine);
        System.out.println("退出代码: " + code);
        System.out.println(susStream.toString("GBK"));
        System.out.println(errStream.toString("GBK"));

    }
}
