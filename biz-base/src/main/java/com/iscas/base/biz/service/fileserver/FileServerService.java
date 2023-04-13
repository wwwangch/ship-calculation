package com.iscas.base.biz.service.fileserver;


import com.iscas.templet.exception.BaseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 文件服务service
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/9/25 15:52
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface FileServerService {
    Map<String, String> upload(MultipartFile[] files) throws IOException;

    void download(String path) throws BaseException;
}
