package com.suntoon.map.utils;

import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName Pair
 * @Description 键值对对象
 * @Author YueLifeng
 * @Date 2019/5/5 0005上午 11:12
 * @Version 1.0
 */
public class Pair<K, V> implements Map.Entry<K, V> {

    private K key;

    private V v;

    /**
     * @Author YueLifeng
     * @Description //键值对构造器
     * @Date 上午 11:14 2019/5/5 0005
     * @param key
     *          键
     * @param v
     *          值
     * @return
     */
    public Pair(K key, V v) {
        this.key = key;
        this.v = v;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return this.v;
    }

    @Override
    public V setValue(V value) {
        this.v = value;
        return this.v;
    }
}
