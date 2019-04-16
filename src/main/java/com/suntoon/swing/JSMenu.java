package com.suntoon.swing;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JSMenu
 * @Description 创建一个自动生成的menu菜单对象，采用xml，或者数据库配置的方式
 * @Author YueLifeng
 * @Date 2019/4/16 0016下午 2:35
 * @Version 1.0
 */
public class JSMenu extends JMenu {

    private static final long serialVersionUID = 6168496697551159825L;

    //构造一个没有文本的JMenu
    public JSMenu() {
        this("");
    }

    //用提供的string作为文本构造一个新的JMenu
    public JSMenu(String s) {
        super(s);
    }

    /**
     * @Author YueLifeng
     * @Description //构造属性为Action提供的的一个menu
     * @Date 下午 2:40 2019/4/16 0016
     * @param a
     * @return
     */
    public JSMenu(Action a) {
        this();
        setAction(a);
    }

    /**
     * Constructs a new <code>JMenu</code> with the supplied string as
     * its text and specified as a tear-off menu or not.
     *
     * @param s the text for the menu label
     * @param b can the menu be torn off (not yet implemented)
     */
    public JSMenu(String s, boolean b) {
        this(s);
    }
}
