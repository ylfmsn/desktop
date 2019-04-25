package com.suntoon.swing.table.editor;

import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName JSTablePopupTableEditor
 * @Description 实现的下拉嵌套表格形式的控件
 * @Author YueLifeng
 * @Date 2019/4/25 0025下午 3:12
 * @Version 1.0
 */
public class JSTablePopupTableEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {

    private static final long serialVersionUID = 7641246944239998385L;

    /**
     * 嵌套的table对象
     */
    private JXTable table;

    /**
     * 嵌套的table对象
     * @return
     */
    public JXTable getTable() {
        return table;
    }

    /**
     * 嵌套的table对象
     * @param table
     */
    public void setTable(JXTable table) {
        this.table = table;
    }

    /**
     * 实现的下拉嵌套表格形式的控件
     * @param table 嵌套的表格
     */
    public JSTablePopupTableEditor(JXTable table)
    {
        super();
        this.table = table;
    }

    @Override
    public Object getCellEditorValue() {
        return table.getSelectedRow();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // TODO Auto-generated method stub
        Popup popup = PopupFactory.getSharedInstance().getPopup(table, this.table , 0, 0);
        popup.show();

        return this.table;
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
                                                boolean leaf, int row) {
        // TODO Auto-generated method stub
        return null;
    }
}
