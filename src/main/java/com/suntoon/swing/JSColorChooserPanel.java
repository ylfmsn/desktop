package com.suntoon.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * @ProjectionName desktop
 * @ClassName JSColorChooserPanel
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/15 0015下午 4:13
 * @Version 1.0
 */
public class JSColorChooserPanel extends JPanel {

    private static final long serialVersionUID = 8301312679935345851L;

    //颜色选择控件
    private JColorChooser colorChooser;

    //确定按钮
    private JButton btnOk;

    //取消按钮
    private JButton btnCancle;

    //回调事件
    private ColorChooserListener chooseListener;

    //设置颜色选择事件
    public void addColorChooserListener(ColorChooserListener l) {
        chooseListener = l;
    }

    //移除listener
    public void removeColorChooserListener() {
        chooseListener = null;
    }

    //当前的颜色
    public Color getColor() {
        return colorChooser.getColor();
    }

    public void setColor(Color color) {
        colorChooser.setColor(color);
    }

    //带有颜色的构造函数
    public JSColorChooserPanel(Color color) {
        this();
        this.setColor(color);
    }

    //创建panel
    public JSColorChooserPanel() {
        setLayout(new BorderLayout(0, 0));

        colorChooser = new JColorChooser();
        add(colorChooser, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        btnOk = new JButton("确定");
        panel.add(btnOk);

        btnCancle = new JButton("取消");
        panel.add(btnCancle);

        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooseListener != null) {
                    chooseListener.afterCancle();
                }
            }
        });

        //执行回调
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooseListener != null) {
                    chooseListener.afterChoose(getColor());
                }
            }
        });
    }

    //设置颜色的操作
    public interface ColorChooserListener extends EventListener {
        //点击确定按钮执行操作
        public void afterChoose(Color color);

        //取消操作
        public void afterCancle();
    }


}
