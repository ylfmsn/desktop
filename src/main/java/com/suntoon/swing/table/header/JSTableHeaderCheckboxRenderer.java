package com.suntoon.swing.table.header;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * @ProjectionName desktop
 * @ClassName JSTableHeaderCheckboxRenderer
 * @Description 表头带有选择框的控件
 * @Author YueLifeng
 * @Date 2019/4/22 0022下午 2:17
 * @Version 1.0
 */
public class JSTableHeaderCheckboxRenderer extends JCheckBox implements TableCellRenderer, Serializable {

    private static final long serialVersionUID = -5916141000327482269L;

    //表头带有选择框的控件
    public JSTableHeaderCheckboxRenderer() {
        super();
        this.setHorizontalAlignment(JSTableHeaderCheckboxRenderer.CENTER);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JSTableHeaderCheckboxRenderer.this.setSelected(!JSTableHeaderCheckboxRenderer.this.isSelected());
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
