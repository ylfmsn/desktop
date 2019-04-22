package com.suntoon.swing.table.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.Serializable;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName JSTabelMapRenderer
 * @Description 数据字典类型的显示控件
 * @param <V> 绑定值
 * @param <T> 显示值
 * @Author YueLifeng
 * @Date 2019/4/22 0022上午 10:58
 * @Version 1.0
 */
public class JSTableMapRenderer<V, T> extends DefaultTableCellRenderer implements TableCellRenderer, Serializable {

    private static final long serialVersionUID = 4431452687038719128L;

    //当前的显示值
    private Map<V, T> datas;

    public JSTableMapRenderer(Map<V, T> datas) {
        this.datas = datas;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setValue(value);
        if (value != null) {
            try {
                this.setText(this.datas.get(value).toString());
            } catch (Exception e) {
                e.printStackTrace();
                this.setText("");
            }
        } else {
            this.setText("");
        }

        if (isSelected) {
            this.setForeground(table.getSelectionForeground());
            this.setBackground(table.getSelectionBackground());
        } else {
            this.setForeground(table.getForeground());
            this.setBackground(table.getBackground());
        }

        return this;
    }
}
