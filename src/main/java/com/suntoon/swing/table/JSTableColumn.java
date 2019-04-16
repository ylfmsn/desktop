package com.suntoon.swing.table;

import org.jdesktop.swingx.table.TableColumnExt;

import java.text.Format;

/**
 * @ProjectionName desktop
 * @ClassName JSTableColumn
 * @Description //扩展的列属性对象
 * @Author YueLifeng
 * @Date 2019/4/16 0016上午 10:01
 * @Version 1.0
 */
public class JSTableColumn extends TableColumnExt {

    private static final long serialVersionUID = 7093063365050161392L;

    //原始数据字段
    public static final String COLUMN_ORIGINAL = "col_original";

    //自动生成新行的时候的默认值
    private Object defaultValue;

    /**
     * 在JSTableColumn对象上扩展一个属性canBeNull；
     * 当一些特殊类型，用户设置为true时，比如掩码格式的Date类型的时候，用户清空输入，或者设置为0000-00-00
     * 00:00:00这样的格式后，在后台转换为null； false的时候，提示用户数据的格式不正确，默认样式为单元格边框反红，不允许接受输入。
     */
    private Boolean canBeNull = false;

    //显示的值表达式
    private Format formator;

    //显示值的表达式
    public Format getFormator() {
        return formator;
    }

    public void setFormator(Format formator) {
        this.formator = formator;
    }

    //自动生成新行时候的默认值
    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 在JSTableColumn对象上扩展一个属性canBeNull；
     * 当一些特殊类型，用户设置为true时，比如掩码格式的Date类型的时候，用户清空输入，或者设置为0000-00-00
     * 00:00:00这样的格式后，在后台转换为null； false的时候，提示用户数据的格式不正确，默认样式为单元格边框反红，不允许接受输入。
     */
    public Boolean getCanBeNull() {

        return canBeNull;
    }

    /**
     * 在JSTableColumn对象上扩展一个属性canBeNull；
     * 当一些特殊类型，用户设置为true时，比如掩码格式的Date类型的时候，用户清空输入，或者设置为0000-00-00
     * 00:00:00这样的格式后，在后台转换为null； false的时候，提示用户数据的格式不正确，默认样式为单元格边框反红，不允许接受输入。
     */
    public void setCanBeNull(Boolean canBeNull) {

        this.canBeNull = canBeNull;
    }

    @Override
    public String toString() {
        return this.getIdentifier() == null ? super.toString() : this.getIdentifier().toString();
    }
}
