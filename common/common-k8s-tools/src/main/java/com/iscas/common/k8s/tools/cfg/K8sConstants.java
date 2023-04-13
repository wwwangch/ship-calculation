package com.iscas.common.k8s.tools.cfg;

/**
 * K8S工具相关常量
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/30 10:38
 * @since jdk1.8
 */
public interface K8sConstants {
    /**相差的时间*/
   long TIME_OFFSET = 8L * 3600 * 1000;
//   long TIME_OFFSET = 0L;

   /**时间格式化串*/
   String TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
}
