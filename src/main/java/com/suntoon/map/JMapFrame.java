package com.suntoon.map;

import com.suntoon.map.control.JMapToolBar;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName JMapFrame
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/25 0025下午 4:20
 * @Version 1.0
 */
public class JMapFrame extends JFrame {

    private static final long serialVersionUID = 682799622168380767L;

    //地图工具条
    private JMapToolBar toolBar;

    //地图对象
    private JMapCanvas canvas;

    public JMapFrame(){
        super();
        //this.setSize(1224, 840);
        this.setBounds(100, 100, 1224, 840);
        this.setTitle("suntoon desktoop v1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 默认窗口最大化
        this.initCompents();
    }

    private void initCompents(){

        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1024, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label = new JLabel("林调通", JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        this.add(panel,BorderLayout.NORTH);

        JMapPanel mapPanel = new JMapPanel();
        this.add(mapPanel, BorderLayout.CENTER);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setPreferredSize(new Dimension(300, 700));
        panel1.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        //JLabel label1 = new JLabel("本地数据", JLabel.CENTER);
        panel1.add(new JLocalDataPanel(mapPanel), BorderLayout.CENTER);
        this.add(panel1, BorderLayout.EAST);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(300, 700));
        panel2.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label2 = new JLabel("平板数据", JLabel.CENTER);
        panel2.add(label2, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.WEST);
    }
}
