package com.suntoon.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @ProjectionName desktop
 * @ClassName JSPanelToolBar
 * @Description 顶部工具条
 * @Author YueLifeng
 * @Date 2019/4/16 0016下午 2:43
 * @Version 1.0
 */
public class JSPanelToolBar extends JPanel implements JSToolBarBuilder {

    private static final long serialVersionUID = -4564113028292744141L;

    //顶部工具条
    public JSPanelToolBar() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    }

    /**
     * @Author YueLifeng
     * @Description //直接在工具条上增加按钮
     * @Date 下午 3:13 2019/4/16 0016
     * @param image
     * @param text
     * @param toolTip
     * @param listener
     * @return void
     */
    @Override
    public void addActionListener(String image, String text, String toolTip, ActionListener listener) {
        this.add(this.buildButton(image, text, toolTip, listener));
    }

    /**
     * @Author YueLifeng
     * @Description //直接在工具条上增加按钮对象
     * @Date 下午 3:13 2019/4/16 0016
     * @param action
     * @return void
     */
    @Override
    public void addAction(Action action) {
        this.add(this.buildButton(action));
    }
}
