package com.suntoon.swing.table.editor;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.EventObject;

/**
 * @ProjectionName desktop
 * @ClassName JSTableSpinnerEditor
 * @Description 微调控件editor
 * @Author YueLifeng
 * @Date 2019/4/23 0023上午 10:27
 * @Version 1.0
 */
public class JSTableSpinnerEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {

    private static final long serialVersionUID = -3845493748266516628L;

    //当前系统的控件
    protected JComponent editorComponent;

    //处理从CellEditor发送的所有方法的委托类
    protected EditorDelegate delegate;

    //激活次数
    protected int clickCountToStart = 1;

    /**
     * @Author YueLifeng
     * @Description 用文本字段构建DefaultCellEditor
     * @Date 下午 2:41 2019/4/23 0023
     * @param textField
     *          JTextField对象
     * @return
     */
    public JSTableSpinnerEditor(final JSpinner textField) {
        editorComponent = textField;
        this.clickCountToStart = 2;
        delegate = new EditorDelegate() {

            private static final long serialVersionUID = -577821741970648323L;

            @Override
            public void setValue(Object value) {
                textField.setValue(value);
            }

            @Override
            public Object getCellEditorValue() {
                return textField.getValue();
            }
        };
    }

    //系统默认的微调控件
    public JSTableSpinnerEditor() {
        this(new JSpinner());
    }

    //返回一个编辑器组件的引用
    public Component getComponent() {
        return editorComponent;
    }

    /**
     * @Author YueLifeng
     * @Description 指定开启编辑时的鼠标点击数
     * @Date 下午 2:45 2019/4/23 0023
     * @param count
     *          指定开始编辑所需的单击次数
     * @return void
     */
    public void setClickCountToStart(int count) {
        clickCountToStart = count;
    }

    //返回开始编辑所需的点击次数
    public int getClickCountToStart() {
        return clickCountToStart;
    }

    //将消息从CellEditor转发给委托
    @Override
    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
    }

    //将消息从CellEditor转发给委托
    @Override
    public boolean isCellEditable(EventObject e) {
        return delegate.isCellEditable(e);
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return delegate.shouldSelectCell(anEvent);
    }

    @Override
    public boolean stopCellEditing() {
        return delegate.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
        delegate.cancelCellEditing();
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        String stringValue = tree.convertValueToText(value, isSelected, expanded, leaf, row, false);
        delegate.setValue(stringValue);
        return editorComponent;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        delegate.setValue(value);
        return editorComponent;
    }

    protected class EditorDelegate extends JSEditorDelegateAdapter {

        private static final long serialVersionUID = -3567056544985065197L;

        @Override
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

        @Override
        public void cancelCellEditing() {
            fireEditingCanceled();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JSTableSpinnerEditor.this.stopCellEditing();
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            JSTableSpinnerEditor.this.stopCellEditing();
        }
    }



}
