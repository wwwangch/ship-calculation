package com.iscas.common.tools.core.io.file;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

/**
 * @author admin
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "DeprecatedIsStillUsed", "unused", "rawtypes", "unchecked"})
@Deprecated
public class PropsUtils {

     public PropsUtils() {

     }

    public String getPropsFilePath()
    {
        String filePath = Objects.requireNonNull(this.getClass().getResource("/")).getPath();
        filePath = filePath.substring(0, filePath.indexOf("classes")-1) + "/destinations.properties";
        return filePath;
    }

    public InputStream getPropsIS()
    {
        return this.getClass().getResourceAsStream("/destinations.properties");
    }



    /**
     * 获取字符型属性值
     * @since jdk1.8
     * @date 2021/1/6
     * @param attr 属性key
     * @return java.lang.String
     */
    public String readSingleProps(String attr){
        String retValue;
        Properties props = new Properties();
        try {
            InputStream fi = getPropsIS();
            props.load(fi);
            fi.close();

            retValue = props.getProperty(attr);
        } catch (Exception e) {
            return "";
        }
        return retValue;
    }

    /**
     * 获取所有属性
     * @since jdk1.8
     * @date 2021/1/6
     * @return java.util.HashMap
     */
    public HashMap readAllProps(){

        HashMap h = new HashMap(16);
        Properties props = new Properties();

        try {

            InputStream fi = getPropsIS();
            props.load(fi);
            fi.close();
            Enumeration er = props.propertyNames();
            while (er.hasMoreElements()) {
                String paramName = (String) er.nextElement();
                h.put(paramName, props.getProperty(paramName));
            }
        } catch (Exception e) {
            return new HashMap(0);
        }
        return h;
    }
}
