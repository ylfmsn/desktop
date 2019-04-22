package com.suntoon.swing.table.renderer;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JSTableExpressionRenderer
 * @Description 表达式渲染器
 * @Author YueLifeng
 * @Date 2019/4/22 0022上午 10:29
 * @Version 1.0
 */
public class JSTableExpressionRenderer extends JSTableDefaultCellRenderer {

    private static final long serialVersionUID = 8993416282846463368L;

    //当前的el表达式
    private Expression el;

    public Expression getEl() {
        return el;
    }

    public void setEl(Expression el) {
        this.el = el;
    }

    public JSTableExpressionRenderer(Expression el) {
        super();
        this.el = el;
    }

    //设置显示值
    @Override
    protected void setValue(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (this.el != null) {
            setText(el.getText(table, value, isSelected, hasFocus, row, column));
        } else {
            setText((value == null) ? "" : value.toString());
        }
    }

    //获取值
    public interface Expression {
        public String getText(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column);
    }
}
