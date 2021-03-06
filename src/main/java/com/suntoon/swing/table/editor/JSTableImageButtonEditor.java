package com.suntoon.swing.table.editor;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableImageButtonEditor
 * @Description 图片按钮编辑器
 * @Author YueLifeng
 * @Date 2019/4/25 0025下午 3:08
 * @Version 1.0
 */
public class JSTableImageButtonEditor extends AbstractCellEditor implements TreeCellEditor, TableCellEditor {

    private static final long serialVersionUID = -1287059888797387629L;

    /**
     * 操作代理对象
     */
    private EditorDelegate delegate;

    /**
     * 当前的编辑控件
     */
    protected JButton editorComponent;

    protected ActionListener actionLinster;

    /**
     * 带有初始化控件对象的操作
     *
     * @param editor
     */
    public JSTableImageButtonEditor(JButton editor) {
        this.editorComponent = editor;
        delegate = new EditorDelegate();
        delegate.setClickCountToStart(1);

        editorComponent.addActionListener(delegate);
    }

    /**
     * 不带参数的默认构造函数
     */
    public JSTableImageButtonEditor(Icon icon) {
        this(new JButton(icon));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
                                                boolean leaf, int row) {

        String stringValue = tree.convertValueToText(value, isSelected, expanded, leaf, row, false);

        delegate.setValue(stringValue);
        return editorComponent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        if (isSelected) {
            editorComponent.setBackground(table.getSelectionBackground());
        } else {
            editorComponent.setBackground(table.getBackground());
        }

        delegate.setValue(value);
        return editorComponent;
    }

    /**
     * 实现的内部方法
     *
     * @author sam
     *
     */
    protected class EditorDelegate extends JSEditorDelegateAdapter {

        private static final long serialVersionUID = -9163652875049976071L;

        /**
         * 值发生改变的时候执行的操作
         */
        public void setValue(Object value) {
            super.setValue(value);
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
        @Override
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
        @Override
        public void actionPerformed(ActionEvent e) {
            JSTableImageButtonEditor.this.stopCellEditing();
        }

        /**
         * When an item's state changes, editing is ended.
         *
         * @param e
         *            the oldaction event
         * @see #stopCellEditing
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            JSTableImageButtonEditor.this.stopCellEditing();
        }
    }

}
