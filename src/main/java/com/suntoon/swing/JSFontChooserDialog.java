package com.suntoon.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * @ProjectionName desktop
 * @ClassName JSFontChooserDialog
 * @Description //颜色选择对话框
 * @Author YueLifeng
 * @Date 2019/4/16 0016上午 10:25
 * @Version 1.0
 */
public class JSFontChooserDialog extends JSDialog {

    private static final long serialVersionUID = -4073734321825991766L;

    //确定按钮
    private JButton btnOk;

    //取消按钮
    private JButton btnCancle;

    //字体选择器
    private JSFontChooserPanel fontChooser;

    //回调事件
    private FontChooserListener fontListener;

    /**
     * @Author YueLifeng
     * @Description //设置颜色选择事件
     * @Date 下午 2:18 2019/4/16 0016
     * @param l
     * @return void
     */
    public void addFontChooserListener(FontChooserListener l) {
        fontListener = l;
    }

    /**
     * @Author YueLifeng
     * @Description //移除listener
     * @Date 下午 2:18 2019/4/16 0016
     * @param
     * @return void
     */
    public void removeFontChooserListener() {
        fontListener = null;
    }

    /**
     * @Author YueLifeng
     * @Description //颜色选择对话框
     * @Date 下午 2:31 2019/4/16 0016
     * @param
     * @return
     */
    public JSFontChooserDialog() {
        super();
        setFont(new Font("宋体", Font.PLAIN, 12));
        initCompents();
    }

    //设置字体
    public void setSelectFont(Font font) {
        if (fontChooser != null)
            fontChooser.setSelectFont(font);
    }

    /**
     * @Author YueLifeng
     * @Description //初始化控件的操作
     * @Date 下午 2:19 2019/4/16 0016
     * @param
     * @return void
     */
    private void initCompents() {

        getContentPane().setLayout(new BorderLayout());
        setTitle("字体选择器");

        fontChooser = new JSFontChooserPanel();
        getContentPane().add(fontChooser, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        btnOk = new JButton("确定");
        panel.add(btnOk);

        btnCancle = new JButton("取消");
        panel.add(btnCancle);

        this.setModal(true);    //指定此对话框是否应为模态对话框, 模态对话框意味着该窗口打开时其他窗口都被屏蔽了, 非模态则全部可以点击
        this.setLocationRelativeTo(null);   //相对于指定控件的位置
        this.setSize(390, 297);
        this.setResizable(false);    //设置对话框是否允许用户调整大小

        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fontListener != null) {
                    fontListener.afterCancle();
                }
                setVisible(false);
            }
        });

        //执行回调
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fontListener != null) {
                    fontListener.afterChoose(fontChooser.getFont(), fontChooser.getFontDecode());
                }
                setVisible(false);
            }
        });
    }

    /**
     * @Author YueLifeng
     * @Description //设置颜色的操作
     * @Date 下午 2:15 2019/4/16 0016

     * @return
     */
    public interface FontChooserListener extends EventListener {

        //点击确定的按钮执行的操作
        void afterChoose(Font font, String fontDecode);

        //取消操作
        default void afterCancle(){};
    }
}
