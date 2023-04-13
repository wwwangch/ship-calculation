package com.iscas.templet.dynamic;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/4/8 14:26
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public class DynamicBean implements Serializable {

    /**
     * 实体Object
     */
    protected Object object = null;

    /**
     * 属性map
     */
    protected BeanMap beanMap = null;

    public DynamicBean() {
        super();
    }

    public DynamicBean(Map propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    /**
     * 给bean属性赋值
     *
     * @param property 属性名
     * @param value    值
     */
    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 通过属性名得到属性值
     *
     * @param property 属性名
     * @return 值
     */
    public Object getValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 得到该实体bean对象
     *
     * @return Object
     */
    public Object getObject() {
        return this.object;
    }

    /**
     * @param propertyMap 属性
     * @return Object
     */
    private Object generateBean(Map propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Object o : keySet) {
            String key = (String) o;
            generator.addProperty(key, (Class) propertyMap.get(key));
        }
        return generator.create();
    }

    public void dynamicAddProps(Map propertyMap) throws IllegalAccessException {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Object o : keySet) {
            String key = (String) o;
            generator.addProperty(key, (Class) propertyMap.get(key));
        }


        Class<? extends DynamicBean> aClass = this.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            generator.addProperty(declaredField.getName(), declaredField.getType());
        }
        this.object = generator.create();
        this.beanMap = BeanMap.create(this.object);
        for (Field declaredField : declaredFields) {
            //防止被漏洞软件扫描出漏洞，更改授权方式 add by zqw 2021-12-08
            makeAccessible(declaredField, this);
            setValue(declaredField.getName(), declaredField.get(this));
        }

    }

    private void makeAccessible(Field declaredField, Object obj) {
        boolean b = (!Modifier.isPublic(declaredField.getModifiers()) ||
                !Modifier.isPublic(declaredField.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(declaredField.getModifiers())) && !declaredField.canAccess(obj);
        if (b) {
            declaredField.setAccessible(true);
        }
    }

    public Map convertToMap() throws IllegalAccessException {
        Map map = new LinkedHashMap();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //如果是这俩属性就不进行转化了，否则转化入Map
            if (!Objects.equals(declaredField.getName(), "object") &&
                    !Objects.equals(declaredField.getName(), "beanMap")) {
                //防止被漏洞软件扫描出漏洞，更改授权方式 add by zqw 2021-12-08
                makeAccessible(declaredField, this);

                map.put(declaredField.getName(), declaredField.get(this));
            }
        }
        if (beanMap != null) {
            for (Object key : beanMap.keySet()) {
                map.put(String.valueOf(key), this.getValue(String.valueOf(key)));
            }
        }
        return map;
    }

}
