package com.suntoon.swing;

import javax.swing.*;
import java.util.Vector;

/**
 * @ProjectionName desktop
 * @ClassName JSComboBox
 * @Description //拓展的swing JComboBox下拉列表框，可以绑定代码操作
 * @Author YueLifeng
 * @Date 2019/4/15 0015下午 5:10
 * @Version 1.0
 */
public class JSComboBox<T, V> extends JComboBox<T> {

    private static final long serialVersionUID = -6360305558680319285L;

    //绑定的数据列表
    protected Vector<V> values;

    //无数据的下拉列表框
    public JSComboBox() {
        this(new Vector<T>(), new Vector<V>());
    }

    /**
     * @Author YueLifeng
     * @Description //实现绑定值和显示值2个操作 请按照次序绑定上
     * @Date 下午 5:13 2019/4/15 0015
     * @param items   显示值
     * @param datas   绑定值
     * @return
     */
    public JSComboBox(T[] items, V[] datas) {
        super(items);
        values = new Vector<V>(datas.length);
        int i, c;
        for (i = 0, c = items.length; i< c; i++) {
            values.addElement(datas[i]);
        }
    }

    /**
     * @Author YueLifeng
     * @Description //实现绑定值和显示值2个操作 请按照次序绑定上
     * @Date 下午 5:15 2019/4/15 0015
     * @param items   显示值
     * @param datas   绑定值
     * @return
     */
    public JSComboBox(Vector<T> items, Vector<V> datas) {
        super(items);
        this.values = datas;
    }

    /**
     * @Author YueLifeng
     * @Description //获取当前选中的节点对应的值
     * @Date 下午 5:18 2019/4/15 0015
     * @param
     * @return V
     */
    public V getSelectedValue() {
        int index = this.getSelectedIndex();

        if (index < 0) {
            return null;
        }

        if (this.values == null || this.values.size() <= 0 || index >= this.values.size()) {
            return null;
        }

        return values.get(this.getSelectedIndex());
    }
}
