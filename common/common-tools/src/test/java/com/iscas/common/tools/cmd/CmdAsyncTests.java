package com.iscas.common.tools.cmd;

import org.apache.commons.exec.*;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.Consumer;

/**
 * 异步调用测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/24 19:06
 * @since jdk1.8
 */
public class CmdAsyncTests {

    //JDK自带的Runtime的API不支持异步执行，如果要异步拿到执行结果需要自己单独创建线程不断轮询进程状态然后通知主线程，
    // 下面看一个例子。例子力求简单，所以很多细节不是很严谨，只看大体思路即可（如果要实现exec方便的API需要更多的代码来实现）。
    @Test
    public void testJdk() throws IOException, InterruptedException {
        System.out.println("1. 开始执行");
        String cmd = "cmd /c ping localhost"; // 假设是一个耗时的操作
        execAsync(cmd, processResult -> {
            System.out.println("3. 异步执行完成，success=" + processResult.success + "; msg=" + processResult.result);
            System.exit(0);
        });
        // 做其他操作 ... ...
        System.out.println("2. 做其他操作");
        // 避免主线程退出导致程序退出
        Thread.currentThread().join();
    }

    private void execAsync(String command, Consumer<ProcessResult> callback) throws IOException {
        final Process process = Runtime.getRuntime().exec(command);
        new Thread(() -> {
            StringBuilder successMsg = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"))) {
                // 存放临时结果
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        successMsg.append(line).append("\r\n");
                        int exitCode = process.exitValue();
                        ProcessResult pr = new ProcessResult();
                        if (exitCode == 0) {
                            pr.success = true;
                            pr.result = successMsg.toString();
                        } else {
                            pr.success = false;
                            pr.result = IOUtils.toString(process.getErrorStream(), "utf-8");
                        }
                        callback.accept(pr); // 回调主线程注册的函数
                        break; // exitValue没有异常表示进程执行完成，退出循环
                    } catch (IllegalThreadStateException e) {
                        // 异常代表进程没有执行完
                    }
                    try {
                        // 等待100毫秒在检查是否完成
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private static class ProcessResult {
        boolean success;
        String result;
    }

    //commons-exec原生支持异步调用，下面直接看例子。
    @Test
    public void testCommonExec() throws InterruptedException, IOException {
        String command = "ping localhost";
        //接收正常结果流
        ByteArrayOutputStream susStream = new ByteArrayOutputStream();
        //接收异常结果流
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        CommandLine commandLine = CommandLine.parse(command);
        DefaultExecutor exec = new DefaultExecutor();


        PumpStreamHandler streamHandler = new PumpStreamHandler(susStream, errStream);
        exec.setStreamHandler(streamHandler);
        ExecuteResultHandler erh = new ExecuteResultHandler() {
            @Override
            public void onProcessComplete(int exitValue) {
                try {
                    String suc = susStream.toString("GBK");
                    System.out.println(suc);
                    System.out.println("3. 异步执行完成");
                } catch (UnsupportedEncodingException uee) {
                    uee.printStackTrace();
                }
            }
            @Override
            public void onProcessFailed(ExecuteException e) {
                try {
                    String err = errStream.toString("GBK");
                    System.out.println(err);
                    System.out.println("3. 异步执行出错");
                } catch (UnsupportedEncodingException uee) {
                    uee.printStackTrace();
                }
            }
        };
        System.out.println("1. 开始执行");
        exec.execute(commandLine, erh);
        System.out.println("2. 做其他操作");
        // 避免主线程退出导致程序退出
        Thread.currentThread().join();
    }


}
