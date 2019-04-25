package com.suntoon.map;

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

    public JMapFrame(){
        super();
        //this.setSize(1224, 840);
        this.setBounds(100, 100, 1224, 840);
        this.setTitle("suntoon desktoop v1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
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

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setPreferredSize(new Dimension(200, 700));
        panel1.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label1 = new JLabel("本地数据", JLabel.CENTER);
        panel1.add(label1, BorderLayout.CENTER);
        this.add(panel1,BorderLayout.EAST);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(200, 700));
        panel2.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label2 = new JLabel("平板数据", JLabel.CENTER);
        panel2.add(label2, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.WEST);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label3 = new JLabel("map", JLabel.CENTER);
        panel3.add(label3, BorderLayout.CENTER);
        this.add(panel3,BorderLayout.CENTER);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(200, 40));
        panel4.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label4 = new JLabel("map", JLabel.CENTER);
        panel4.add(label4, BorderLayout.CENTER);
        this.add(panel4,BorderLayout.SOUTH);
    }
    public static void main(String args[]){
        JMapFrame test = new JMapFrame();
    }
}
