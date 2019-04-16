package com.suntoon.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * @ProjectionName desktop
 * @ClassName JSColorChooserDialog
 * @Description //颜色选择对话框
 * @Author YueLifeng
 * @Date 2019/4/15 0015下午 3:47
 * @Version 1.0
 */
public class JSColorChooserDialog extends JSDialog {

    private static final long serialVersionUID = -4073734321825991766L;

    //颜色选择对象
    private JColorChooser colorChooser;

    //确定按钮
    private JButton btnOk;

    //取消按钮
    private JButton btnCancle;

    //当前选中的颜色
    public Color getColor() {
        return colorChooser.getColor();
    }

    public void setColor(Color color) {
        this.colorChooser.setColor(color);
    }

    //回调事件
    private ColorChooserListener chooserListener;

    //设置颜色选择事件
    public void addColorChooserListener (ColorChooserListener l) {
        chooserListener = l;
    }

    //移除listener
    public void removeColorChooserListener() {
        chooserListener = null;
    }

    //颜色选择对话框
    public JSColorChooserDialog() {
        super();
        setFont(new Font("宋体", Font.PLAIN, 12));
        initCompents();
    }

    //带有颜色参数的构造函数
    public JSColorChooserDialog(Color color) {
        this();
        this.setColor(color);
    }

    //初始化控件
    private void initCompents() {
        getContentPane().setLayout(new BorderLayout(0, 0));
        setTitle("颜色选择器");

        getContentPane().add(colorChooser, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        btnOk = new JButton("确定");
        panel.add(btnOk);

        btnCancle = new JButton("取消");
        panel.add(btnCancle);

        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setSize(580, 400);
        this.setResizable(false);

        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooserListener != null) {
                    chooserListener.afterCancle();
                }
                setVisible(false);
            }
        });

        //执行回调
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooserListener != null) {
                    chooserListener.afterChoose(getColor());
                }
                setVisible(false);
            }
        });
    }


    public interface ColorChooserListener extends EventListener {
        //点击确定的按钮执行的操作
        public void afterChoose(Color color);

        //取消操作
        public default void afterCancle() {};
    }


}
