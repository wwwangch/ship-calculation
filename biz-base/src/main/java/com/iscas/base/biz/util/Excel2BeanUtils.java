package com.iscas.base.biz.util;

import com.iscas.templet.exception.BaseException;
import com.iscas.common.tools.office.excel.ExcelUtils;
import com.iscas.common.tools.core.reflect.ReflectUtils;
import com.iscas.templet.exception.Exceptions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * excel转为Java Bean工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/5 19:07
 * @since jdk1.8
 */
@SuppressWarnings({"DeprecatedIsStillUsed", "unchecked", "rawtypes"})
@Deprecated
public class Excel2BeanUtils {
    private Excel2BeanUtils(){}

    /**
     *
     * @since jdk1.8
     * @date 2018/9/5
     * @param inputStream 输入流
     * @param configPath 映射配置文件， 中文 英文的txt
     * @param clazz 实体类型
     * @throws Exception 异常
     * @return java.util.List<T>
     */
    public static <T> List<T> excel2Bean(InputStream inputStream, String configPath, Class<T> clazz) throws Exception {

        Map<String, Object> excelContent = ExcelUtils.readExcelWithHeader(inputStream);
        List<String> headerList = (List<String>)excelContent.get("header");
        List<String> templeteHeaderList = new ArrayList<>();
        List<T> list = new ArrayList<>();
        Resource resource = new ClassPathResource(configPath);
        InputStream is  = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = null;
        Map<String, String> mapping = new LinkedHashMap<>();
        try {
            br = new BufferedReader(inputStreamReader);
            String line;
            while((line = br.readLine()) !=null){
                line = line.trim();
                String key = line.substring(0, line.indexOf(" "));
                String value = line.substring(line.indexOf(" ")).trim();
                templeteHeaderList.add(key);
                mapping.put(key, value);
            }

        }finally {
            assert br != null;
            br.close();
            inputStreamReader.close();
            is.close();
        }

        if(!(new HashSet<>(headerList).equals(new HashSet<>(templeteHeaderList)))){
            throw Exceptions.baseException("指南Excel模板错误，请重新下载模板！");
        }


        Map<String, List> excelMaps = (Map<String, List>)excelContent.get("data");
        Collection<List> values = excelMaps.values();
        List<Map> excelMapData = values.iterator().next();
        if(CollectionUtils.isNotEmpty(excelMapData)){
            for (Map map: excelMapData) {
                for (Map.Entry<String, String> entry: mapping.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if(map.containsKey(key)){
                        //设置为对应类型
                        map.put(value, map.get(key));
                        map.remove(key);
                    }
                }
                T t = (T) ReflectUtils.convertMap2Bean(map, clazz);
                list.add(t);
            }
        }
        inputStream.close();
        return list;
    }
}
