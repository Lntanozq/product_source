package com.briup.framework.util;


import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * vanse(lc)
 * 实体转换工具类
 */
public class EntityUtils {

    /**
     * 实体转VM
     *
     * @param source           原对象
     * @param vmClass          要转换的对象
     * @param ignoreProperties 忽略的属性
     * @param <T>              泛型
     * @return 转换后对象
     */
    public static <T> T entity2VM(Object source, Class<T> vmClass, String... ignoreProperties) {
        if (null == source) {
            return null;
        }
        try {
            T target = vmClass.newInstance();
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * VM转实体
     * 底层用的vm2Entity，只是方法名做区分
     *
     * @param source           vm
     * @param entClass         实体
     * @param ignoreProperties 忽略的属性
     * @param <T>              泛型
     * @return 转换后的对象
     */
    public static <T> T vm2Entity(Object source, Class<T> entClass, String... ignoreProperties) {
        return entity2VM(source, entClass, ignoreProperties);
    }


    /**
     * Entity VM 互转
     *
     * @param object      数据源
     * @param laterObject 转换对象
     * @param <T>         泛型
     */
    public static <T> void copyProperties(final T object, T laterObject) {

        if (null == object || null == laterObject) {
            return;
        }

        ConcurrentHashMap<String, Method> getMethods = findGetMethods(object.getClass().getMethods());

        ConcurrentHashMap<String, Method> setMethods = findSetMethods(laterObject.getClass().getDeclaredMethods());

        Iterator<Map.Entry<String, Method>> iterator = getMethods.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Method> entry = iterator.next();
            String methodName = entry.getKey();
            Method getMethod = entry.getValue();
            Method setMethod = setMethods.get(methodName);
            if (null == setMethod) {
                continue;
            }
            try {
                Object value = getMethod.invoke(object, new Object[]{});
                setMethod.invoke(laterObject, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取所有的get方法
     *
     * @param methods 所有的方法
     * @return 所有的get方法
     */
    private static ConcurrentHashMap<String, Method> findGetMethods(Method[] methods) {
        ConcurrentHashMap<String, Method> getMethodsMap = new ConcurrentHashMap<>();
        for (Method method : methods) {
            if (isGetMethod(method.getName())) {
                getMethodsMap.put(getMethodName(method.getName()), method);
            }
        }
        return getMethodsMap;
    }

    /**
     * 获取所有的set方法
     *
     * @param methods 所有的方法
     * @return 所有的set方法
     */
    private static ConcurrentHashMap<String, Method> findSetMethods(Method[] methods) {
        ConcurrentHashMap<String, Method> setMethodsMap = new ConcurrentHashMap<>();
        for (Method method : methods) {
            if (isSetMethod(method.getName())) {
                setMethodsMap.put(getMethodName(method.getName()), method);
            }
        }
        return setMethodsMap;
    }


    /**
     * 取方法名
     *
     * @param getMethodName 方法名称
     * @return 去掉get set的方法名
     */
    private static String getMethodName(String getMethodName) {
        String fieldName = getMethodName.substring(3, getMethodName.length());
        return fieldName;
    }

    /**
     * 判断是否是get方法
     *
     * @param methodName
     * @return
     */
    private static boolean isGetMethod(String methodName) {
        int index = methodName.indexOf("get");
        if (index == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是set方法
     *
     * @param methodName 方法名
     * @return 是否为set 方法
     */
    private static boolean isSetMethod(String methodName) {
        int index = methodName.indexOf("set");
        if (index == 0) {
            return true;
        }
        return false;
    }

}
