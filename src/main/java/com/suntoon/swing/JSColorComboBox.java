package com.suntoon.swing;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JSColorComboBox
 * @Description //下拉颜色框
 * @Author YueLifeng
 * @Date 2019/4/15 0015下午 5:07
 * @Version 1.0
 */
public class JSColorComboBox extends JComboBox<JSColorChooserPanel> {

    //颜色选择对象
    protected JSColorChooserPanel colorChooser;

    private static final long serialVersionUID = -931021026684581413L;

    //下拉颜色框
    public JSColorComboBox() {
        super();
        this.initCompents();
    }

    private void initCompents() {
        colorChooser = new JSColorChooserPanel();
        this.addItem(colorChooser);
    }
}