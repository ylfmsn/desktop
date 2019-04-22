package com.suntoon.swing.table.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName JSTableRowNumberRenderer
 * @Description 行号渲染器
 * @Author YueLifeng
 * @Date 2019/4/22 0022下午 2:13
 * @Version 1.0
 */
public class JSTableRowNumberRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = -7120562179856374728L;

    /**
     * @Author YueLifeng
     * @Description //行号渲染器， 默认居中
     * @Date 下午 2:15 2019/4/22 0022
     * @param
     * @return
     */
    public JSTableRowNumberRenderer() {
        super();
        this.setHorizontalAlignment(JSTableRowNumberRenderer.CENTER);
    }

    //直接返回行号渲染
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        this.setText(String.valueOf(row + 1));
        return this;
    }
}
