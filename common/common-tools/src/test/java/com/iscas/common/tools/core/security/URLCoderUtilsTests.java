package com.iscas.common.tools.core.security;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

/**
 * 编码工具类测试
 * @author zhuquanwen
 **/
public class URLCoderUtilsTests {
    /**
     * url编码
     * */
    @Test
    public void urlEncode() throws UnsupportedEncodingException {
        String url = "https://translate.google.cn/#en/zh-CN/generate?xx=你好啊啊啊";
        String result = URLCoderUtils.utf8URLencode(url);
        Assertions.assertNotNull(result);
    }
    /**
     * url解码
     * */
    @Test
    public void urlDecode(){
        String url = "https://translate.google.cn/#en/zh-CN/generate?xx=%E4%BD%A0%E5%A5%BD%E5%95%8A%E5%95%8A%E5%95%8A";
        if(URLCoderUtils.isUtf8Url(url)){
            String result = URLCoderUtils.utf8URLdecode(url);
            Assertions.assertNotNull(result);
        }else {
            Assertions.assertNotNull(null);
        }
    }

}
