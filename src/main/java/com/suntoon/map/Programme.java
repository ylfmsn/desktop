package com.suntoon.map;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName Programme
 * @Description 程序主入口
 * @Author YueLifeng
 * @Date 2019/4/25 0025下午 3:58
 * @Version 1.0
 */
public class Programme {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JMapFrame frame = new JMapFrame();
        frame.setVisible(true);
    }
}
