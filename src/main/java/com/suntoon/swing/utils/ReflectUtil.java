package com.suntoon.swing.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ProjectionName desktop
 * @ClassName ReflectUtil
 * @Description 和反射相关的工具类
 * @Author YueLifeng
 * @Date 2019/4/19 0019下午 4:17
 * @Version 1.0
 */
public class ReflectUtil {

    /**
     * @Author YueLifeng
     * @Description //执行get method
     * @Date 下午 5:29 2019/4/19 0019
     * @param entity
     * @param colName
     * @return com.suntoon.swing.utils.Pair<java.lang.Boolean,java.lang.Object>
     */
    public static Pair<Boolean, Object> invokeGetMethod(Object entity, String colName) {
        if (entity == null)
            return null;

        try {
            //先判断get函数，没有get函数，执行is函数
            Method method = null;
            Class<?> cls = entity.getClass();

            try {
                method = cls.getMethod("get" + colName.substring(0, 1).toUpperCase() + colName.substring(1));
            } catch (Exception ex) {
                method = null;
            }

            if (method == null)
                method = cls.getMethod("is" + colName.substring(0, 1).toUpperCase() + colName.substring(1));

            if (method != null)
                return new Pair<Boolean, Object>(true, method.invoke(entity));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new Pair<Boolean, Object>(false, null);
    }

    /**
     * @Author YueLifeng
     * @Description //获取set method执行方法
     * @Date 下午 5:32 2019/4/19 0019
     * @param entity
     * @param colName
     * @return java.lang.reflect.Method
     */
    public static Method getMethod(Object entity, String colName) {
        if (entity == null)
            return null;

        try {
            Method method = null;
            Class<?> cls = entity.getClass();
            //先判断get函数，没有get函数，执行is函数
            try {
                method = cls.getMethod("get" + colName.substring(0, 1).toUpperCase() + colName.substring(1));
            } catch (Exception ex) {
                method = null;
            }

            if (method == null) {
                method = cls.getMethod("is" + colName.substring(0, 1).toUpperCase() + colName.substring(1));
            }

            return method;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * @Author YueLifeng
     * @Description //获取set method执行函数
     * @Date 下午 5:38 2019/4/19 0019
     * @param entity
     * @param colName
     * @return java.lang.reflect.Method
     */
    public static Method getSetMethod(Object entity, String colName) {
        if (entity == null)
            return null;

        try {
            Method method = null;
            Class<?> cls = entity.getClass();
            //先判断get函数，没有get函数，执行is函数
            try {
                method = cls.getMethod("get" + colName.substring(0, 1).toUpperCase() + colName.substring(1));
            } catch (Exception ex) {
                method = null;
            }

            if (method == null) {
                method = cls.getMethod("is" + colName.substring(0, 1).toUpperCase() + colName.substring(1));
            }

            return cls.getMethod("set" + colName.substring(0, 1).toUpperCase() + colName.substring(1),
                    method.getReturnType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * @Author YueLifeng
     * @Description //获取实体上
     * @Date 下午 5:44 2019/4/19 0019
     * @param entity
     * @param fieldName
     * @return java.lang.String
     */
    public static String getDisplay(Object entity, String fieldName) {
        if (entity == null)
            return "";

        if (StringUtils.isEmpty(fieldName))
            return "";

        try {
            //带有嵌套效果的对象数据
            if (fieldName.indexOf('.') >= 0) {

                String[] objs = fieldName.split("\\.");
                Object object = entity;

                for (int i = 0; i < objs.length; i++) {
                    Field field = object.getClass().getDeclaredField(objs[i]);
                    field.setAccessible(true);
                    object = field.get(object);
                }

                return object == null ? "" : object.toString();
            } else {
                Field field = entity.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object object = field.get(entity);
                return object == null ? "" : object.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }
}
