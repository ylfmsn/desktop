package com.suntoon.swing.table.editor;

import com.suntoon.swing.JSButtonIcon;
import com.suntoon.swing.JSColorChooserDialog;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableColorEditor
 * @Description 颜色编辑器
 * @Author YueLifeng
 * @Date 2019/4/24 0024上午 10:58
 * @Version 1.0
 */
public class JSTableColorEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {

    private static final long serialVersionUID = -1287059888797387629L;

    //操作代理对象
    private EditorDelegate delegate;

    //当前的编辑控件
    protected JButton editorComponent;

    //colorchoose对象
    protected JSColorChooserDialog colorChooser;

    //带有初始化控件对象的操作
    public JSTableColorEditor(JButton button) {
        this.editorComponent = button;
    }

    //不带参数的默认构造器
    public JSTableColorEditor() {
        this(new JButton(new JSButtonIcon()));

        delegate = new EditorDelegate();
        delegate.setClickCountToStart(2);

        editorComponent.addActionListener(delegate);

        if (colorChooser == null)
            colorChooser = new JSColorChooserDialog();

        this.colorChooser.addColorChooserListener(new JSColorChooserDialog.ColorChooserListener() {
            @Override
            public void afterChoose(Color color) {
                delegate.setValue(color);
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
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

        if (isSelected) {
            JSButtonIcon icon = (JSButtonIcon) editorComponent.getIcon();
            colorChooser.setColor(icon.getIconColor());
            colorChooser.setVisible(true);
        }

        return editorComponent;
    }

    protected class EditorDelegate extends JSEditorDelegateAdapter {

        private static final long serialVersionUID = -9163652875049976071L;

        //值放生改变的时候执行的操作
        @Override
        public void setValue(Object value) {
            super.setValue(value);

            JSButtonIcon icon = (JSButtonIcon) editorComponent.getIcon();

            try {
                if (value == null) {
                    icon.setIconColor(Color.BLACK);
                    return;
                }

                if (value instanceof Integer) {
                    icon.setIconColor(new Color((Integer) value));
                } else if (value instanceof Long) {
                    icon.setIconColor(new Color(((Long) value).intValue()));
                } else if (value instanceof String) {
                    icon.setIconColor(Color.getColor(value.toString()));
                } else if (value instanceof Color) {
                    icon.setIconColor((Color) value);
                }
            } catch (Exception ex) {
                icon.setIconColor(Color.BLACK);
            }
        }

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
            JSTableColorEditor.this.stopCellEditing();
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            JSTableColorEditor.this.stopCellEditing();
        }
    }
}
