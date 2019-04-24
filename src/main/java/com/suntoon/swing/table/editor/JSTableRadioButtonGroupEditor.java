package com.suntoon.swing.table.editor;

import com.suntoon.swing.JSRadioButtonGroup;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.EventObject;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName JSTableRadioButtonGroupEditor
 * @Description 单选按钮组editor
 * @Author YueLifeng
 * @Date 2019/4/23 0023下午 3:14
 * @Version 1.0
 */
public class JSTableRadioButtonGroupEditor<V, T> extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {

    private static final long serialVersionUID = 263310385939006964L;

    //当前操作的控件
    protected JSRadioButtonGroup<V, T> editorComponent;

    //事件代理对象
    protected EditorDelegate delegate;

    //无默认选中值的构造器
    public JSTableRadioButtonGroupEditor(Map<V, T> map) {
        if (map == null)
            throw new IllegalArgumentException();

        editorComponent = new JSRadioButtonGroup<V, T>(map);
        delegate = new EditorDelegate() {
            private static final long serialVersionUID = -4724768865052360104L;

            @Override
            public void setValue(Object value) {
                super.setValue(value);
            }

            @Override
            public Object getCellEditorValue() {
                return editorComponent.getSelectedValue();
            }
        };
    }

    /**
     * @Author YueLifeng
     * @Description 带有默认值的构造器
     * @Date 下午 4:25 2019/4/23 0023
     * @param map
     *          数据字典
     * @param defaultValue
     *          默认选中值
     * @return
     */
    public JSTableRadioButtonGroupEditor(Map<V, T> map, V defaultValue) {
        this(map);
        editorComponent.setSelectedValue(defaultValue);
    }

    /**
     * @Author YueLifeng
     * @Description 带有按钮方向的构造器
     * @Date 下午 4:27 2019/4/23 0023
     * @param map
     *          数据字典
     * @param defaultValue
     *          默认值
     * @param axis
     *          0 x方向； 1 y方向
     * @return
     */
    public JSTableRadioButtonGroupEditor(Map<V, T> map, V defaultValue, int axis) {
        this(map);
        editorComponent.setSelectedValue(defaultValue);
        editorComponent.setLayoutAxis(axis);
    }

    //鼠标操作多少次启动控件的操作
    public void setClickCountToStart(int count) {
        delegate.setClickCountToStart(count);
    }

    //鼠标操作多少次启动控件的操作
    public int getClickCountToStart() {
        return delegate.getClickCountToStart();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    @Override
    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.AbstractCellEditor#isCellEditable(java.util.EventObject)
     */
    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return delegate.isCellEditable(anEvent);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.swing.AbstractCellEditor#shouldSelectCell(java.util.EventObject)
     */
    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return delegate.shouldSelectCell(anEvent);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.AbstractCellEditor#stopCellEditing()
     */
    @Override
    public boolean stopCellEditing() {
        if (editorComponent.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "请选择单选按钮");
            return false;
        }
        return delegate.stopCellEditing();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.AbstractCellEditor#cancelCellEditing()
     */
    @Override
    public void cancelCellEditing() {
        delegate.cancelCellEditing();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.swing.tree.TreeCellEditor#getTreeCellEditorComponent(javax.swing.
     * JTree, java.lang.Object, boolean, boolean, boolean, int)
     */
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
                                                boolean leaf, int row) {
        String stringValue = tree.convertValueToText(value, isSelected, expanded, leaf, row, false);

        delegate.setValue(stringValue);
        return editorComponent;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing
     * .JTable, java.lang.Object, boolean, int, int)
     */
    @SuppressWarnings("unchecked")
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        delegate.setValue(value);
        editorComponent.setSelectedValue((V) value);

        return editorComponent;
    }

    //实现的内部方法
    protected class EditorDelegate extends JSEditorDelegateAdapter {

        private static final long serialVersionUID = -9163652875049976071L;

        //停止编辑，返回true便是编辑已经停止，该方法调用的是fireEditingStopped
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

        //取消编辑，该方法调用的是fireEditingCanceled
        @Override
        public void cancelCellEditing() {
            fireEditingCanceled();
        }

        //当某一行为事件发生时编辑停止
        @Override
        public void actionPerformed(ActionEvent e) {
            JSTableRadioButtonGroupEditor.this.stopCellEditing();
        }

        //当某一元素状态发上改变时编辑停止
        @Override
        public void itemStateChanged(ItemEvent e) {
            JSTableRadioButtonGroupEditor.this.stopCellEditing();
        }
    }






}
