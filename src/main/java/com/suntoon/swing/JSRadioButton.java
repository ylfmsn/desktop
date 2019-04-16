package com.suntoon.swing;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JSRadioButton
 * @Description 带有绑定值的radio对象
 * @Author YueLifeng
 * @Date 2019/4/16 0016下午 3:13
 * @Version 1.0
 */
public class JSRadioButton extends JRadioButton {

    private static final long serialVersionUID = 1549941455677458829L;

    //绑定值
    private Object tag;

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    //创建一个初始化为没有设置文本的未选中状态的radio button
    public JSRadioButton() {
        this(null, null, null, false);
    }

    /**
     * @Author YueLifeng
     * @Description //只有绑定值的控件
     * @Date 下午 3:28 2019/4/16 0016
     * @param tag   绑定值
     * @return
     */
    public JSRadioButton(Object tag) {
        this(tag == null ? "" : tag.toString(), null, tag, false);
    }

    /**
     * @Author YueLifeng
     * @Description //带有图标 绑定值的构造器
     * @Date 下午 3:29 2019/4/16 0016
     * @param icon   图标
     * @param tag    绑定值
     * @return
     */
    public JSRadioButton(Icon icon, Object tag) {
        this(null, icon, tag, false);
    }

    /**
     * @Author YueLifeng
     * @Description //创建一个由action提供属性值的radiobuttion
     * @Date 下午 3:31 2019/4/16 0016

     * @return
     */
    public JSRadioButton(Action a) {
        this();
        setAction(a);
    }

    /**
     * @Author YueLifeng
     * @Description //图标 绑定值 是否选中的构造器
     * @Date 下午 3:33 2019/4/16 0016
     * @param icon   图标
     * @param tag    绑定值
     * @param selected    是否选中
     * @return
     */
    public JSRadioButton(Icon icon, Object tag, boolean selected) {
        this(null, icon, tag, selected);
    }

    /**
     * @Author YueLifeng
     * @Description //显示值 绑定值的构造函数
     * @Date 下午 3:34 2019/4/16 0016
     * @param text   显示值
     * @param tag    绑定值
     * @return
     */
    public JSRadioButton(String text, Object tag) {
        this(text, null, tag, false);
    }

    /**
     * @Author YueLifeng
     * @Description //是否选中的构造器
     * @Date 下午 3:35 2019/4/16 0016
     * @param text   文本
     * @param tag   绑定值
     * @param selected   是否选中
     * @return
     */
    public JSRadioButton(String text, Object tag, boolean selected) {
        this(text, null, tag, selected);
    }

    /**
     * @Author YueLifeng
     * @Description //默认不选中的所有构造函数
     * @Date 下午 3:36 2019/4/16 0016
     * @param text    文本
     * @param icon    图标
     * @param tag     绑定值
     * @return
     */
    public JSRadioButton(String text, Icon icon, Object tag) {
        this(text, icon, tag, false);
    }

    /**
     * @Author YueLifeng
     * @Description //带有全部参数的构造函数
     * @Date 下午 3:25 2019/4/16 0016
     * @param text
     *          显示文本
     * @param icon
     *          图标
     * @param tag
     *          绑定值
     * @param selected
     *          是否选中
     * @return
     */
    public JSRadioButton(String text, Icon icon, Object tag, boolean selected) {
        super(text, icon, selected);
        this.setTag(tag);
        setBorderPainted(false);    //设置是否显示按钮边框
        setHorizontalAlignment(LEADING);   //文字为从右往左读
    }
}
