package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.*;
import io.fabric8.kubernetes.client.dsl.internal.LogWatchCallback;
import lombok.Cleanup;
import okhttp3.*;
import okio.ByteString;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * 命令处理
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/14 15:55
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class KcExecUtils {
    private KcExecUtils() {
    }

    public static final int LOG_WATCH_LAST_LINES = 200;


    public static InputStream downloadFile(String namespace, String podName, String containerName, String filePath, KubernetesClient kc) throws FileNotFoundException {
        ContainerResource<LogWatch, InputStream, PipedOutputStream, OutputStream, PipedInputStream, String, ExecWatch, Boolean, InputStream, Boolean> containerResource = kc.pods().inNamespace(namespace).withName(podName).inContainer(containerName);
        CopyOrReadable<Boolean, InputStream, Boolean> file = containerResource.file(filePath);
        return file.read();
    }

    public static void downloadDir(String namespace, String podName, String containerName, String filePath, File targetFile) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        ContainerResource<LogWatch, InputStream, PipedOutputStream, OutputStream, PipedInputStream, String, ExecWatch, Boolean, InputStream, Boolean> containerResource = kc.pods().inNamespace(namespace).withName(podName).inContainer(containerName);
        CopyOrReadable<Boolean, InputStream, Boolean> file = containerResource.dir(filePath);
        file.copy(targetFile.toPath());
    }

    public static void uploadFile(String namespace, String podName, String containerName, String filePath, File targetFile) throws FileNotFoundException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        ContainerResource<LogWatch, InputStream, PipedOutputStream, OutputStream, PipedInputStream, String, ExecWatch, Boolean, InputStream, Boolean> containerResource = kc.pods().inNamespace(namespace).withName(podName).inContainer(containerName);
        CopyOrReadable<Boolean, InputStream, Boolean> file = containerResource.file(filePath);
        file.upload(targetFile.toPath());
    }


    public static LogWatch traceLog(String namespace, String podName, String containerName, int tailingLines) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        ContainerResource<LogWatch, InputStream, PipedOutputStream, OutputStream, PipedInputStream, String, ExecWatch, Boolean, InputStream, Boolean> containerResource = kc.pods().inNamespace(namespace).withName(podName).inContainer(containerName);
        CopyOrReadable<Boolean, InputStream, Boolean> file = containerResource.file("");
        LogWatch logWatch1 = containerResource.tailingLines(tailingLines).watchLog();
        LogWatchCallback logWatchCallback = (LogWatchCallback) logWatch1;
        logWatchCallback.waitUntilReady();
        return logWatch1;
    }


    public static String getLog(String namespace, String podName, String containerName) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        ContainerResource<LogWatch, InputStream, PipedOutputStream, OutputStream, PipedInputStream, String, ExecWatch, Boolean, InputStream, Boolean> containerResource = kc.pods().inNamespace(namespace)
                .withName(podName)
                .inContainer(containerName);
        return containerResource.getLog();
    }

    public static String getLog(String namespace, String podName, String containerName, int beforeMinutes, int maxSize) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        ContainerResource<LogWatch, InputStream, PipedOutputStream, OutputStream, PipedInputStream, String, ExecWatch, Boolean, InputStream, Boolean> containerResource = kc.pods().inNamespace(namespace).withName(podName).inContainer(containerName);
        return containerResource.limitBytes(maxSize * 1024 * 1024).sinceSeconds(beforeMinutes * 60).getLog();
    }

    private static class MyPodExecListener implements ExecListener {
        @Override
        public void onOpen(Response response) {
            System.out.println("Shell was opened");
        }

        @Override
        public void onFailure(Throwable throwable, Response response) {
            System.out.println("Some error encountered");
//            execLatch.countDown();
        }

        @Override
        public void onClose(int i, String s) {
            System.out.println("Shell Closing");
//            execLatch.countDown();
        }
    }

    public static void test3() {
        String url = "wss://192.168.100.95:6443/api/v1/namespaces/default/pods/product-test-9556d9667-clz5n/exec?stdin=true&stdout=true&stderr=true&tty=true&command=%2Fbin%2Fsh&container=product-test-instance";
//        String url = "wss://192.168.100.95:6443/api/v1/namespaces/default/pods/product-test-9556d9667-clz5n/exec?stdin=true&stdout=true&stderr=true&tty=true";
        KubernetesClient kc = K8sClient.getInstance();
        OkHttpClient httpClient = ((DefaultKubernetesClient) kc).getHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        //开始连接
        WebSocket websocket = httpClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(@Nonnull WebSocket webSocket, @Nonnull Response response) {
                super.onOpen(webSocket, response);
                //连接成功...
                System.out.println(1111);
            }

            @Override
            public void onMessage(@Nonnull WebSocket webSocket, @Nonnull String text) {
                super.onMessage(webSocket, text);
                //收到消息...（一般是这里处理json）
                System.out.println(2222);
            }

            @Override
            public void onMessage(@Nonnull WebSocket webSocket, @Nonnull ByteString bytes) {
                super.onMessage(webSocket, bytes);
                //收到消息...（一般很少这种消息）
                System.out.println(3333);
                System.out.println(new String(bytes.toByteArray()));
            }

            @Override
            public void onClosed(@Nonnull WebSocket webSocket, int code, @Nonnull String reason) {
                super.onClosed(webSocket, code, reason);
                //连接关闭...
                System.out.println(44444);
            }

            @Override
            public void onFailure(@Nonnull WebSocket webSocket, @Nonnull Throwable throwable, Response response) {
                super.onFailure(webSocket, throwable, response);
                //连接失败...
                throwable.printStackTrace();
                System.out.println(55555);
            }
        });
        try {
            TimeUnit.MILLISECONDS.sleep(3800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean success = websocket.send("pwd");
        boolean success2 = websocket.send("ls");
        System.out.println(success);
        httpClient.dispatcher().executorService().shutdown();
    }

    public static void test4() {
        String url = "wss://192.168.100.95:6443/api/v1/namespaces/default/pods/product-test-9556d9667-clz5n/exec?stdin=true&stdout=true&stderr=true&tty=true&command=%2Fbin%2Fsh&container=product-test-instance";
        KubernetesClient kc = K8sClient.getInstance();
        OkHttpClient httpClient = ((DefaultKubernetesClient) kc).getHttpClient();
        FormBody.Builder builder = new FormBody.Builder();

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        requestBuilder.post(builder.build());
        Request request = requestBuilder.build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@Nonnull Call call, @Nonnull IOException e) {
                System.out.println(111);
            }

            @Override
            public void onResponse(@Nonnull Call call, @Nonnull Response response) {
                System.out.println(2222);
            }
        });
    }

}
