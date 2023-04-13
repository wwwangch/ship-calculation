package com.iscas.base.biz.util.echarts;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iscas.common.tools.constant.CharsetConstant;
import com.iscas.templet.exception.Exceptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 参考：<a href="https://www.jianshu.com/p/dfc28fd7d786">https://www.jianshu.com/p/dfc28fd7d786</a>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/24 21:00
 * @since jdk1.8
 */
public class EchartsUtils {
    private static final String SUCCESS_CODE = "1";

    public static String generateEchartsBase64(String phantomjsUrl, String option) throws IOException {
        String base64 = "";
        if (option == null) {
            return base64;
        }
        option = option.replaceAll("\\s+", "").replaceAll("\"", "'");

        // 将option字符串作为参数发送给echartsConvert服务器
        Map<String, String> params = new HashMap<>(2);
        params.put("opt", option);
        String response = HttpUtil.post(phantomjsUrl, params, CharsetConstant.UTF8);

        // 解析echartsConvert响应
        JSONObject responseJson = JSON.parseObject(response);
        String code = responseJson.getString("code");

        if (SUCCESS_CODE.equals(code)) {
            // 如果echartsConvert正常返回
            base64 = responseJson.getString("data");
        } else {
            // 未正常返回
            String string = responseJson.getString("msg");
            throw Exceptions.runtimeException(string);
        }
        return base64;
    }
}
