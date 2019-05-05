package com.suntoon.map.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName RangeHashMap
 * @Description 范围hashmap在区间范围上取值的hashmap
 * @Author YueLifeng
 * @Date 2019/5/5 0005上午 11:12
 * @Version 1.0
 */
public class RangeHashMap<K, V> extends HashMap<K, V> {

    public static final long serialVersionUID = -1615435488644017718L;

    //初始化表示的范围
    private List<K> range = new LinkedList<>();

    //获取当前的表示范围 不允许修改， 所以转化为array给用户
    public Object[] getRange() {
        return range.toArray();
    }

    //无参构造器
    public RangeHashMap() {
        super();
    }

    /**
     * @Author YueLifeng
     * @Description //
     * @Date 上午 11:36 2019/5/5 0005
     * @param ks
     * @param vs
     * @return
     */
    public RangeHashMap(K[] ks, V[] vs) throws Exception {
        super();

        if (ks == null || vs == null) {
            throw new IllegalAccessException("keys and values not can be null");
        }

        if (ks.length != vs.length) {
            throw new IllegalAccessException("keys and values size must be equals");
        }

        for (int i = 0; i < ks.length; i++) {
            range.add(ks[i]);
            this.put(ks[i], vs[i]);
        }
    }

    //重写的获取数据的方法
    @Override
    public V get(Object key) {
        if (this.containsKey(key))
            return super.get(key);

        for (int i = 0; i < this.range.size() - 1; i++) {
            K k = range.get(i);
            if (k instanceof Comparable) {
                K k1 = range.get(i + 1);
                int i1 = ((Comparable<Object>) k).compareTo(key);
                int i2 = ((Comparable<Object>) k1).compareTo(key);
                if ((i1 + i2) == 0)
                    return super.get(k);
            }
        }

        return super.get(range.get(this.range.size() - 1));
    }

    //加入新数据
    @Override
    public V put(K key, V value) {
        this.range.add(key);
        return super.put(key, value);
    }
}