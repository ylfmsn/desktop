package com.suntoon.swing.utils;

import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName Pair
 * @Description 键值对对象
 * @Author YueLifeng
 * @Date 2019/4/19 0019下午 4:28
 * @Version 1.0
 */
public class Pair<K, V> implements Map.Entry<K, V> {

    private K key;

    private V v;

    public Pair(K key, V v) {
        this.key = key;
        this.v = v;
    }


    @Override
    public K getKey() {
        return this.key;
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
