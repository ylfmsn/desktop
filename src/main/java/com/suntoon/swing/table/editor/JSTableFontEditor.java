package com.suntoon.swing.table.editor;

import com.suntoon.swing.JSFontChooserDialog;
import com.suntoon.swing.JSFontChooserDialog.FontChooserListener;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableFontEditor
 * @Description 字体选择编辑器
 * @Author YueLifeng
 * @Date 2019/4/24 0024下午 2:04
 * @Version 1.0
 */
public class JSTableFontEditor extends AbstractCellEditor implements TreeCellEditor, TableCellEditor {

    private static final long serialVersionUID = 757140289750579347L;

    //操作代理对象
    private EditorDelegate delegate;

    //当前的编辑控件
    protected JButton editorComponent;

    //colorchooser对象
    protected JSFontChooserDialog fontChooser;

    //带有初始化控件对象的操作
    public JSTableFontEditor(JButton button) {
        this.editorComponent = button;
    }

    public JSTableFontEditor() {
        this(new JButton());

        delegate = new EditorDelegate();
        delegate.setClickCountToStart(2);

        editorComponent.addActionListener(delegate);

        if (fontChooser == null)
            fontChooser = new JSFontChooserDialog();

        this.fontChooser.addFontChooserListener(new FontChooserListener() {

            //点击确定后执行的操作
            @Override
            public void afterChoose(Font font, String fontDecode) {
                if (delegate.getCellEditorValue() instanceof Font) {
                    delegate.setValue(font);
                } else if (delegate.getCellEditorValue() instanceof String) {
                    delegate.setValue(fontDecode);
                } else {
                    delegate.setValue(fontDecode);
                }
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
            if (value == null) {
                fontChooser.setVisible(true);
            } else {
                try {

                    if (value instanceof String) {
                        fontChooser.setSelectFont(Font.decode(value.toString()));
                    } else if (value instanceof Font) {
                        fontChooser.setSelectFont((Font) value);
                    }

                    fontChooser.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return editorComponent;
    }

    //实现的内部方法
   protected class EditorDelegate extends JSEditorDelegateAdapter {

       private static final long serialVersionUID = -9163652875049976071L;

       //值发生改变的时候执行的操作
       @Override
       public void setValue(Object value) {
           super.setValue(value);

           try {
               editorComponent.setText(value == null ? "" : value.toString());
           } catch (Exception ex) {
               editorComponent.setText("");
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
            JSTableFontEditor.this.stopCellEditing();
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            JSTableFontEditor.this.stopCellEditing();
        }
    }
}
