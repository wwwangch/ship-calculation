package com.iscas.base.biz.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.iscas.common.tools.core.random.RandomStringUtils;
import com.iscas.templet.exception.Exceptions;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/2/21 14:11
 * @since jdk1.8
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "rawtypes", "SuspiciousMethodCalls", "unused"})
public class ApiSignUtils {
    public static String DEFAULT_SECRET_KEY = "ISCAS123";

    private static final String TIME_STAMP_KEY = "timeStamp";

    /**随机字符串，防止重放攻击*/
    private static final String NONCE_KEY = "nonce";
    private static final String SIGN_KEY = "sign";
    /**超时时效，超过此时间认为签名过期*/
    private static final Long EXPIRE_TIME = 5 * 60 * 1000L;


    /**
     * 生成签名
     */
    public static Map getSignature(Object param, String secretKey) {
        Map<String, Object> params;
        try {
            params = jsonToMap(JSONObject.toJSONString(param));
        } catch (Exception e) {
            throw Exceptions.runtimeException("生成签名：转换json失败");
        }

        if (params.get(TIME_STAMP_KEY) == null) {
            params.put(TIME_STAMP_KEY, String.valueOf(System.currentTimeMillis()));
        }
        if (params.get(NONCE_KEY) == null) {
            // 生成一个16位的字符串，防止重放攻击
            params.put(NONCE_KEY, RandomStringUtils.randomStr(16));
        }
        //对map参数进行排序生成参数
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        Map<String, Object> finalParams = params;
        String temp = Arrays.stream(keys).map(key -> key + "=" + (!finalParams.containsKey(key) ? "" : JSONObject.toJSONString(finalParams.get(key))))
                .collect(Collectors.joining("&"));
        //根据参数生成签名
        String sign = DigestUtils.sha256Hex(temp + secretKey).toUpperCase();
        params.put(SIGN_KEY, sign);
        return params;
    }

    /**
     * 校验签名
     */
    public static boolean checkSignature(HttpServletRequest request, String secretKey) throws IOException {
        //获取request中的json参数转成map
        Map<String, Object> param = getAllParams(request);

        String sign = (String) param.get(SIGN_KEY);
        String nonce = (String) param.get(NONCE_KEY);
        Long start = convertTimestamp(param.get(TIME_STAMP_KEY));
        long now = System.currentTimeMillis();
        //校验时间有效性
        if (start == null || now - start > EXPIRE_TIME || start - now > 0L) {
            return false;
        }

        // 校验重放攻击，从缓存中取nonce,如果存在,直接拒绝，如果不存在，放入缓存，失效时间与EXPIRE_TIME一致
        // todo 校验nonce根据实际使用的缓存来实现

        //是否携带签名
        if (StringUtils.isBlank(sign)) {
            return false;
        }
        //获取除签名外的参数
        param.remove(SIGN_KEY);
        //校验签名
        Map paramMap = getSignature(param, secretKey);
        String signature = (String) paramMap.get("sign");
        return sign.equals(signature);
    }

    private static Long convertTimestamp(Object timestampObj) {
        return timestampObj != null ? Long.parseLong(timestampObj.toString()) : null;
    }


    /**
     * 将URL的参数和body参数合并
     */
    public static SortedMap<String, Object> getAllParams(HttpServletRequest request) throws IOException {

        //获取URL上的参数,并放入结果中
        SortedMap<String, Object> result = new TreeMap<>(getUrlParams(request));

        // get请求和DELETE请求不需要拿body参数
        if (!StringUtils.equalsAny(request.getMethod(), HttpMethod.GET.name(), HttpMethod.DELETE.name())) {
            Optional.of(getBodyParams(request))
                    .ifPresent(result::putAll);
        }
        return result;
    }


    /**
     * 获取 Body 参数
     */
    public static Map<String, Object> getBodyParams(final HttpServletRequest request) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String str;
        StringBuilder wholeStr = new StringBuilder();
        //一行一行的读取body体里面的内容；
        while ((str = reader.readLine()) != null) {
            wholeStr.append(str);
        }
        //转化成json对象
        return StringUtils.isNoneBlank(wholeStr) ? jsonToMap(wholeStr.toString()) : new TreeMap<>();
    }

    /**
     * JSON转顺序排序的Map
     *
     * @param jsonStr 原始json
     * @return 响应的map
     */
    public static Map<String, Object> jsonToMap(String jsonStr) {
        Map<String, Object> treeMap = new TreeMap<>();
        //Feature.OrderedField实现解析后保存不乱序
        JSONObject json = JSONObject.parseObject(jsonStr, Feature.OrderedField);
        for (String key : json.keySet()) {
            Object value = json.get(key);
            //判断传入kay-value中是否含有""或null
            if (json.get(key) == null || value == null || value.toString().length() == 0) {
                //当JSON字符串存在null时,不将该kay-value放入Map中,即显示的结果不包括该kay-value
                continue;
            }
            // 判断是否为JSONArray(json数组)
            if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                List<Object> arrayList = new ArrayList<>();
                for (Object object : jsonArray) {
                    // 判断是否为JSONObject，如果是 转化成TreeMap
                    if (object instanceof JSONObject) {
                        object = jsonToMap(object.toString());
                    }
                    arrayList.add(object);
                }
                treeMap.put(key, arrayList);
            } else {
                //判断该JSON中是否嵌套JSON
                boolean flag = isJSONValid(value.toString());
                if (flag) {
                    //若嵌套json了,通过递归再对嵌套的json(即子json)进行排序
                    value = jsonToMap(value.toString());
                }
                // 其他基础类型直接放入treeMap
                // JSONObject可进行再次解析转换
                treeMap.put(key, value);
            }
        }
        return treeMap;
    }

    /**
     * 校验是否是JSON字符串
     *
     * @param json 传入数据
     * @return 是JSON返回true, 否则false
     */
    public static boolean isJSONValid(String json) {
        try {
            JSONObject.parseObject(json);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    /**
     * 将URL请求参数转换成Map
     */
    public static Map<String, String> getUrlParams(HttpServletRequest request) {
        return Optional.ofNullable(request.getQueryString())
                .map(queryStr -> URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8))
                .map(params -> Arrays.stream(params.split("&"))
                        .collect(Collectors.toMap(s -> s.substring(0, s.indexOf("=")), s -> s.substring(s.indexOf("=") + 1))))
                .orElse(new HashMap<>(0));

    }


}
