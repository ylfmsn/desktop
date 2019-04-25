package com.suntoon.swing;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName App
 * @Description 演示代码
 * @Author YueLifeng
 * @Date 2019/4/22 0022下午 5:19
 * @Version 1.0
 */
public class App {

    public static void main(String[] args) {

        try {
            //获取当前操作系统的主题
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrameDefaultTableDemo frm = new JFrameDefaultTableDemo();
        frm.setSize(1024, 768);
        frm.setLocationRelativeTo(null);   //在屏幕上居中
        frm.setVisible(true);

        /*JFrameDemo frame = new JFrameDemo();
        frame.*/










    }




}
