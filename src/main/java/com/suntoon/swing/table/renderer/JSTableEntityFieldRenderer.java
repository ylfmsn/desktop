package com.suntoon.swing.table.renderer;

import com.suntoon.swing.utils.ReflectUtil;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * @ProjectionName desktop
 * @ClassName JSTableEntityFieldRenderer
 * @Description 使用实体字段方式显示的渲染器
 * @Author YueLifeng
 * @Date 2019/4/22 0022上午 10:26
 * @Version 1.0
 */
public class JSTableEntityFieldRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = -959451058213640129L;

    //实体对象名称
    private String fieldName = "";

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    //使用实体字段方式显示的渲染器
    public JSTableEntityFieldRenderer(String fieldName) {
        super();
        this.setFieldName(fieldName);
    }

    @Override
    protected void setValue(Object value) {
        setText(ReflectUtil.getDisplay(value, fieldName));
    }
}
