package com.iscas.common.tools.core.net;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/27 8:44
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class UriUtils {
    private UriUtils() {
    }

    /**
     * 从url中获取host和port
     * @since jdk1.8
     * @date 2021/8/27
     * @param url url
     * @return com.iscas.common.tools.core.net.UriUtils.UriInfo
     */
    public static UriInfo getHostPort(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            int port = uri.getPort();
            if (port == -1) {
                port = url.startsWith("http://") ? 80 : url.startsWith("https://") ? 443 : -1;
            }
            return new UriInfo(host, port);
        } catch (URISyntaxException e) {
            return null;
        }
    }


    @Data
    @AllArgsConstructor
    public static class UriInfo {
        private String host;
        private int port;
    }

}
