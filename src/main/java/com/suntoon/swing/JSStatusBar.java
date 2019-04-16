package com.suntoon.swing;

import com.suntoon.swing.utils.FontUtil;
import org.jdesktop.swingx.JXStatusBar;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName JSStatusBar
 * @Description 系统自定义的控件
 * @Author YueLifeng
 * @Date 2019/4/16 0016下午 3:44
 * @Version 1.0
 */
public class JSStatusBar extends JXStatusBar {

    private static final long serialVersionUID = -3123560890720613883L;

    //自定义的状态栏
    public JSStatusBar() {
        super();
        initCompents();
    }

    //工具条
    private JProgressBar progressBar = new JProgressBar();

    //工具条
    private final JPanel contentPanel = new JPanel();

    //空格显示
    private final JLabel labelEmpty = new JLabel("       ");

    //工具条上的提示信息
    private JLabel lblTip = new JLabel("");

    /**
     * @Author YueLifeng
     * @Description //标签文本
     * @Date 下午 3:48 2019/4/16 0016
     * @param font
     * @return void
     */
    @Override
    public void setFont(Font font) {
        this.setFont(font);
        labelEmpty.setFont(font);
        //labelEmpty.setFont(font);
    }

    /**
     * @Author YueLifeng
     * @Description //初始化控件
     * @Date 下午 3:49 2019/4/16 0016
     * @param
     * @return void
     */
    private void initCompents() {
        this.setLayout(new BorderLayout());

        progressBar.setMaximum(100);
        progressBar.setMinimum(0);

        this.setFont(FontUtil.getDefaultFont());
        labelEmpty.setFont(this.getFont());
        lblTip.setFont(this.getFont());

        this.add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPanel.add(lblTip);
        contentPanel.add(progressBar);
        contentPanel.add(labelEmpty);

        progressBar.setVisible(false);
    }
}
