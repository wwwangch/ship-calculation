package com.iscas.base.biz.util;

import java.io.File;
import java.util.concurrent.*;

/**
 * 文件删除工具类，开启一个单线程池，防止并发出错。
 *未用到，将在未来版本删除。
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/6 9:11
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaThreadShouldSetName")
@Deprecated
public class FileDeleteUtils {
    private static final ExecutorService ES = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());
    private FileDeleteUtils(){}
    public static boolean delete(File file) throws ExecutionException, InterruptedException {
        Future<Boolean> submit = ES.submit(file::delete);
        return submit.get();
    }
}
