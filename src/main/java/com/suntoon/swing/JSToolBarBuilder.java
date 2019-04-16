package com.suntoon.swing;

import com.suntoon.swing.resource.ResourceLoader;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @ProjectionName desktop
 * @InterfaceName JSToolBarBuilder
 * @Description 按钮生成工具类
 * @Author ylf
 * @Date 2019/4/16 0016下午 2:44
 * @Version 1.0
 */
public interface JSToolBarBuilder {

    /**
     * @Author YueLifeng
     * @Description //创建自定义的按钮
     * @Date 下午 2:46 2019/4/16 0016
     * @param image
     * @param text
     * @param listener
     * @return javax.swing.JButton
     */
    public default JButton buildButton(String image, String text, ActionListener listener) {
        return buildButton(image, text, "", listener);
    }

    /**
     * @Author YueLifeng
     * @Description //创建自定义的按钮
     * @Date 下午 2:46 2019/4/16 0016
     * @param image
     * @param text
     * @param toolTip
     * @param listener
     * @return javax.swing.JButton
     */
    public default JButton buildButton(String image, String text, String toolTip, ActionListener listener) {
        final JButton btn = new JButton(text, new ImageIcon(ResourceLoader.getResource(image)));
        btn.addActionListener(listener);
        btn.setToolTipText(toolTip);
        btn.setFocusable(true);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setDefaultCapable(true);
        return btn;
    }

    /**
     * @Author YueLifeng
     * @Description //使用action对象创建button对象
     * @Date 下午 3:07 2019/4/16 0016
     * @param action
     * @return javax.swing.JButton
     */
    public default JButton buildButton(Action action) {
        return new JButton(action);
    }

    /**
     * @Author YueLifeng
     * @Description //直接在工具条上增加
     * @Date 下午 3:09 2019/4/16 0016
     * @param image
     * @param text
     * @param toolTip
     * @param listener
     * @return void
     */
    public void addActionListener(String image, String text, String toolTip, ActionListener listener);

    /**
     * @Author YueLifeng
     * @Description //直接在工具条上增加按钮对象
     * @Date 下午 3:09 2019/4/16 0016
     * @param action
     * @return void
     */
    public void addAction(Action action);
}
