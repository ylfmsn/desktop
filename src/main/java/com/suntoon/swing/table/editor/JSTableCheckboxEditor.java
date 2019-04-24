package com.suntoon.swing.table.editor;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JSTableCheckboxEditor
 * @Description 带有多种值类型的checkbox工具
 * @Author YueLifeng
 * @Date 2019/4/23 0023下午 4:47
 * @Version 1.0
 */
public class JSTableCheckboxEditor extends DefaultCellEditor {

    private static final long serialVersionUID = 545401568684102849L;

    //带参数的构造器
    public JSTableCheckboxEditor(JCheckBox checkBox) {
        super(checkBox);
        checkBox.removeActionListener(this.delegate);
        delegate = new EditorDelegate() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setValue(Object value) {
                super.setValue(value);
                boolean isSelected = false;

                if (value instanceof Integer) {
                    isSelected = Integer.valueOf(1).equals(value);
                } else if (value instanceof Long) {
                    isSelected = Long.valueOf(1L).equals(value);
                } else if (value instanceof Boolean) {
                    isSelected = Boolean.TRUE.equals(value);
                } else if (value instanceof String) {
                    isSelected = "true".equals(value);
                } else if (value instanceof Character) {
                    isSelected = Character.valueOf('t').equals(value);
                }

                checkBox.setSelected(isSelected);
            }

            @Override
            public Object getCellEditorValue() {
                return Boolean.valueOf(checkBox.isSelected());
            }
        };

        checkBox.addActionListener(delegate);
        checkBox.setRequestFocusEnabled(false);
    }

    //不带参数的构造器
    public JSTableCheckboxEditor() {
        this(new JCheckBox());
    }
}
