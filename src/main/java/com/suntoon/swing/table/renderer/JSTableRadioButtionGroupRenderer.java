package com.suntoon.swing.table.renderer;

import com.suntoon.swing.JSRadioButtonGroup;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.Serializable;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName JSTableRadioButtionGroupRenderer
 * @Description 单选组对象
 * @Author YueLifeng
 * @Date 2019/4/22 0022下午 1:45
 * @Version 1.0
 */
public class JSTableRadioButtionGroupRenderer<V, T> extends CellRendererPane implements TableCellRenderer, Serializable {

    private static final long serialVersionUID = -4045115531523620178L;

    //单选控件组
    private JSRadioButtonGroup<V, T> radiobuttons;

    //未选中的时候背景颜色
    private Color unselectedForeground;

    //选中时候背景颜色
    private Color unselectedBackground;

    //绑定的数据字典
    private Map<V, T> map;

    /**
     * @Author YueLifeng
     * @Description //只带数据字典的构造器
     * @Date 下午 2:05 2019/4/22 0022
     * @param map
     * @return
     */
    public JSTableRadioButtionGroupRenderer(Map<V,T> map) {
        super();
        this.map = map;
        this.radiobuttons = new JSRadioButtonGroup<>(map);
    }

    public Map<V, T> getMap() {
        return map;
    }

    public void setMap(Map<V, T> map) {
        this.map = map;
    }

    //将未选中的前景色设置为指定的颜色
    @Override
    public void setForeground(Color c) {
        super.setForeground(c);
        unselectedForeground = c;
    }

    //将未选中的背景色设置为指定的颜色
    @Override
    public void setBackground(Color c) {
        super.setBackground(c);
        unselectedBackground = c;
    }

    /**
     * @Author YueLifeng
     * @Description //构造器
     * @Date 下午 2:03 2019/4/22 0022
     * @param map
     *          数据字典
     * @param defaultValue
     *          默认值
     * @return
     */
    public JSTableRadioButtionGroupRenderer(Map<V, T> map, V defaultValue) {
        this(map);
        radiobuttons.setSelectedValue(defaultValue);
    }

    /**
     * @Author YueLifeng
     * @Description //带有数据字典，默认选中值，控件方向的构造器
     * @Date 下午 2:06 2019/4/22 0022
     * @param map
     *          数据字典
     * @param defaultValue
     *          默认值
     * @param axis
     *          0 x方向； 1 y方向
     * @return
     */
    public JSTableRadioButtionGroupRenderer(Map<V, T> map, V defaultValue, int axis) {
        this(map);
        radiobuttons.setSelectedValue(defaultValue);
        radiobuttons.setLayoutAxis(axis);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        radiobuttons.setSelectedValue((V) value);

        JTable.DropLocation dropLoction = table.getDropLocation();
        if (dropLoction != null && !dropLoction.isInsertRow() && !dropLoction.isInsertColumn()
                && dropLoction.getRow() == row && dropLoction.getColumn() == column) {
            isSelected = true;
        }

        if (isSelected) {
            super.setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
            this.radiobuttons.setForeground(table.getSelectionForeground());
            this.radiobuttons.setBackground(table.getSelectionBackground());
        } else {
            super.setForeground(unselectedForeground != null ? unselectedForeground : table.getForeground());
            super.setBackground(unselectedBackground != null ? unselectedBackground : table.getBackground());
            this.radiobuttons.setForeground(this.getForeground());
            this.radiobuttons.setBackground(this.getBackground());
        }

        return radiobuttons;
    }
}
