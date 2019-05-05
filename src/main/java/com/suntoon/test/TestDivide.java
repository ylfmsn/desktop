package com.suntoon.test;

/**
 * @ProjectionName desktop
 * @ClassName TestDivide
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/5 0005下午 3:08
 * @Version 1.0
 */
import javax.swing.*;
import java.awt.*;
public class TestDivide extends JFrame{
    public TestDivide() {
        Container cp=getContentPane();

       /* setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1224, 740);*/


        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 1200, 840);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1200, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label = new JLabel("林调通", JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        frame.add(panel,BorderLayout.NORTH);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(cp, BorderLayout.CENTER);
        frame.add(panel1,BorderLayout.CENTER);
        frame.setVisible(true);


        JSplitPane js = new JSplitPane();
        JSplitPane js1 = new JSplitPane();
        JPanel jp1 = new JPanel();
        jp1.setBackground(Color.green);
        JPanel jp2 = new JPanel();
        jp2.setBackground(Color.yellow);
        JPanel jp3 = new JPanel();

        jp3.setBackground(Color.pink);
        js1.setLeftComponent(jp1);
        js1.setRightComponent(jp2);
        js1.setVisible(true);
        js1.setDividerLocation((double) 0.2);
        js.setLeftComponent(js1);
        js.setRightComponent(jp3);
        js.setVisible(true);
        js.setDividerLocation((double) 0.8);
        cp.add(js);





    }

    public static void main(String sd[]){





        TestDivide frm=new TestDivide();
    }
}
