package com.suntoon.swing.table.editor;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableEmptyEditor
 * @Description 空编辑模式的editor对象
 * @Author YueLifeng
 * @Date 2019/4/25 0025下午 3:05
 * @Version 1.0
 */
public class JSTableEmptyEditor extends AbstractCellEditor implements TreeCellEditor, TableCellEditor {

    private static final long serialVersionUID = 4070221556048242744L;

    /**
     * 当前的控件
     */
    private JLabel label;

    /**
     * 操作代理对象
     */
    private EditorDelegate delegate;

    /**
     * 无参数构造函数
     */
    public JSTableEmptyEditor()
    {
        this("");
    }

    /**
     * 有参数构造函数
     * @param fieldName 字段名称
     */
    public JSTableEmptyEditor(String fieldName)
    {
        super();
        label = new JLabel("");
        delegate = new EditorDelegate();
    }

    /**
     * 设置值
     * @param value
     */
    public void setValue(Object value)
    {
        this.delegate.setValue(value);
        this.label.setText(value == null ? "" : value.toString());
    }

    @Override
    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
                                                boolean leaf, int row) {

        delegate.setValue(value);

        String stringValue = tree.convertValueToText(value, isSelected, expanded, leaf, row, false);
        this.label.setText(stringValue);

        return this.label;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        delegate.setValue(value);

        this.label.setText(value == null ? "" : value.toString());
        return this.label;
    }

    /**
     *
     * @author sam
     *
     */
    protected class EditorDelegate extends JSEditorDelegateAdapter
    {
        private static final long serialVersionUID = 4366510118323198573L;

        public EditorDelegate()
        {
            super();
        }

        /**
         * Stops editing and returns true to indicate that editing has stopped.
         * This method calls <code>fireEditingStopped</code>.
         *
         * @return true
         */
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

        /**
         * Cancels editing. This method calls <code>fireEditingCanceled</code>.
         */
        public void cancelCellEditing() {
            fireEditingCanceled();
        }

        /**
         * When an oldaction is performed, editing is ended.
         *
         * @param e
         *            the oldaction event
         * @see #stopCellEditing
         */
        public void actionPerformed(ActionEvent e) {
            JSTableEmptyEditor.this.stopCellEditing();
        }

        /**
         * When an item's state changes, editing is ended.
         *
         * @param e
         *            the oldaction event
         * @see #stopCellEditing
         */
        public void itemStateChanged(ItemEvent e) {
            JSTableEmptyEditor.this.stopCellEditing();
        }
    }
}
