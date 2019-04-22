package com.suntoon.swing.table.renderer;

import com.suntoon.swing.resource.ResourceLoader;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.Serializable;

/**
 * @ProjectionName desktop
 * @ClassName JSTableImageButtonRenderer
 * @Description 图片按钮渲染器
 * @Author YueLifeng
 * @Date 2019/4/22 0022上午 10:52
 * @Version 1.0
 */
public class JSTableImageButtonRenderer extends JLabel implements TableCellRenderer, Serializable {

    private static final long serialVersionUID = -3684299559581737726L;

    //图片按钮渲染器
    public JSTableImageButtonRenderer(Icon icon) {
        super(icon);
        this.setOpaque(true);   //组件绘制其范围内的每个像素
    }

    //各个默认的向下箭头图标
    public JSTableImageButtonRenderer() {
        this(new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_DOWN_ARROW)));
    }

    //重写对象绘制方法
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            this.setBackground(table.getSelectionBackground());
        } else {
            this.setBackground(table.getBackground());
        }
        return this;
    }
}
