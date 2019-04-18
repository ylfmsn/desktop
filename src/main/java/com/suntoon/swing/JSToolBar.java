package com.suntoon.swing;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @ProjectionName desktop
 * @ClassName JSToolBar
 * @Description 拓展的工具条
 * @Author YueLifeng
 * @Date 2019/4/17 0017下午 3:20
 * @Version 1.0
 */
public class JSToolBar extends JToolBar implements JSToolBarBuilder {

    private static final long serialVersionUID = 6158188823161534815L;

    @Override
    public void addActionListener(String image, String text, String toolTip, ActionListener listener) {
        this.add(this.buildButton(image, text, toolTip, listener));
    }

    @Override
    public void addAction(Action action) {
        this.add(action);
    }
}
