package com.suntoon.swing.table.renderer;

import com.suntoon.swing.resource.ResourceLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.lang.reflect.Field;

/**
 * @ProjectionName desktop
 * @ClassName JSTableEntityFieldButtonRenderer
 * @Description 使用实体字段显示的渲染器
 * @Author YueLifeng
 * @Date 2019/4/22 0022上午 9:42
 * @Version 1.0
 */
public class JSTableEntityFieldButtonRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = -959451058213640129L;

    //实体对象名称
    private String fieldName = "";

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    //按钮
    private JButton btn;

    public JSTableEntityFieldButtonRenderer(String filedName) {
        super();
        this.setLayout(new BorderLayout());
        btn = new JButton(new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_DOWN_ARROW)));
        this.add(btn, BorderLayout.EAST);
        this.setFieldName(filedName);
    }

    //重写赋值部分
    @Override
    protected void setValue(Object value) {
        if (value != null && this.fieldName != null && this.fieldName.length() > 0) {
            try {
                //带有嵌套效果的对象数据
                if (fieldName.indexOf('.') >= 0) {

                    String[] objs = fieldName.split("\\.");
                    Object object = value;

                    for(int i = 0 ; i < objs.length ; i++)
                    {
                        Field field = object.getClass().getDeclaredField(objs[i]);
                        field.setAccessible(true);
                        object = field.get(object);
                    }

                    setText((object == null) ? "" : object.toString());

                } else {
                    Field field = value.getClass().getDeclaredField(this.fieldName);
                    field.setAccessible(true);
                    Object object = field.get(value);
                    setText((object == null) ? "" : object.toString());
                }
            } catch (Exception ex) {
                setText("");
            }

        } else {
            setText("");
        }
    }
}
