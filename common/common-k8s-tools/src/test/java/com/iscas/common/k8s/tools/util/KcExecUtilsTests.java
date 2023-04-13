package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/14 15:56
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcExecUtilsTests {

    @Before
    public void before() throws IOException {
        //初始化
        String configYAML = String.join("\n", Files.readAllLines(new File("C:\\ideaProjects\\cpaas-manager\\kube-config").toPath()));
        Config config = Config.fromKubeconfig(configYAML);
        config.setTrustCerts(true);
        K8sClient.setFabric8Config(config);
    }

//    @Test
//    public void test() throws FileNotFoundException {
//        KcExecUtils.test();
//    }

    @Test
    public void testTraceLog() throws IOException, InterruptedException {
        LogWatch logWatch = KcExecUtils.traceLog("default", "graalvm-test-76d5d69b67-jh5rk", "graalvm-test-instance", KcExecUtils.LOG_WATCH_LAST_LINES);
        InputStream output = logWatch.getOutput();
        byte[] buff = new byte[1024];
        new Thread(() -> {
            while (true) {
                int read = 0;
                try {
                    read = output.read(buff);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.print(new String(buff, 0, read));
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(5000);
        logWatch.close();

    }

    @Test
    public void testTraceLog2() throws IOException {
        for (int i = 0; i < 10; i++) {
            LogWatch logWatch = KcExecUtils.traceLog("default", "graalvm-test-76d5d69b67-jh5rk", "graalvm-test-instance", KcExecUtils.LOG_WATCH_LAST_LINES);
            new Thread(() -> {
                byte[] buff = new byte[1024];
                while (true) {
                    int read = 0;
                    try {
                        read = logWatch.getOutput().read(buff);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.print(new String(buff, 0, read));
                }
            }).start();
            System.out.println(logWatch);
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetLog() throws IOException {
        String logs = KcExecUtils.getLog("default", "product-test-9556d9667-f8tjc", "product-test-instance");
        System.out.println(logs);
    }

//    @Test
//    public void download() throws IOException {
//        KcExecUtils.downloadFile("cm", "nginx-test2-7d6c7c89cf-rl5hn", "nginx-test2-instance0", "/root/xxx", new File("d:/we.txt"));
//    }



}
