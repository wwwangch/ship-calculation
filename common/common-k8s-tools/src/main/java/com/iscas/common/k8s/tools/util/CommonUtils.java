package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.cfg.K8sConstants;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.tools.core.date.DateSafeUtils;
import io.fabric8.kubernetes.api.model.ObjectMeta;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 通用工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 15:00
 * @since jdk1.8
 */
public class CommonUtils {

    private CommonUtils() {}

    /**
     * 获取某个时间点距离当前的时间,并作一些处理
     * */
    public static String getTimeDistance(Date date) {
        if (date == null) {
            return null;
        }
        long start = date.getTime();
        long end = System.currentTimeMillis();
        long distance = end - start;
        if (distance < TimeUnit.MINUTES.toMillis(1)) {
            long l = Math.round(distance/1000.0);
            return "约" + l + "秒";
        } else if (distance >= TimeUnit.MINUTES.toMillis(1)
                    && distance < TimeUnit.HOURS.toMillis(1)) {
            long l = Math.round(distance/(1000.0 * 60));
            return "约" + l + "分钟";
        } else if (distance >= TimeUnit.HOURS.toMillis(1)
                && distance < TimeUnit.DAYS.toMillis(1)) {
            long l = Math.round(distance/(1000.0 * 60 * 60));
            return "约" + l + "小时";
        } else {
            long l = Math.round(distance/(1000.0 * 60 * 60 * 24));
            return "约" + l + "天";
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static Date timeOffset(Date time) {
        if (time == null) {
            return time;
        }
        return new Date(time.getTime() + K8sConstants.TIME_OFFSET);
    }

    public static String getRuntimeStr(ObjectMeta metadata) throws K8sClientException {
        String runtimeStr;
        String creationTimestamp = metadata.getCreationTimestamp();
        Date startTime;
        try {
            startTime = DateSafeUtils.parse(creationTimestamp, K8sConstants.TIME_PATTERN);
            startTime = CommonUtils.timeOffset(startTime);
        } catch (ParseException e) {
            throw new K8sClientException("时间类型转换出错", e);
        }
        runtimeStr = CommonUtils.getTimeDistance(startTime);
        return runtimeStr;
    }

}
