package com.suntoon.swing.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ProjectionName desktop
 * @ClassName ClassUtil
 * @Description 类类型操作对象
 * @Author YueLifeng
 * @Date 2019/4/22 0022下午 5:15
 * @Version 1.0
 */
public abstract class ClassUtil<E> {

    /**
     * 获取一个泛型的实例
     *
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public E getE() throws InstantiationException, IllegalAccessException {
        Class<E> mTClass = getCls();
        return mTClass.newInstance();
    }

    /**
     * 获取当前的泛型的类型
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public Class<E> getCls() {

        Class<E> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            entityClass = (Class<E>)p[0].getClass();
        }

        return entityClass;

    }

}
