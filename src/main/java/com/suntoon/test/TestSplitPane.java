package com.suntoon.test;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName TestSplitPane
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/10 0010上午 10:21
 * @Version 1.0
 */
public class TestSplitPane extends JFrame {
    private static final long serialVersionUID = 682799622168380767L;

    public TestSplitPane(){
        super();
        //this.setSize(1224, 840);
        this.setBounds(100, 100, 1224, 840);
        this.setTitle("suntoon desktoop v1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 默认窗口最大化
        this.initCompents();
        this.setVisible(true);
    }

    private void initCompents(){

        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1024, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label = new JLabel("林调通", JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        this.add(panel,BorderLayout.NORTH);

        /*JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setPreferredSize(new Dimension(300, 700));
        panel1.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label1 = new JLabel("林调通", JLabel.CENTER);
        panel.add(label1, BorderLayout.CENTER);
        this.add(panel1, BorderLayout.EAST);*/

        /*JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(300, 700));
        panel2.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label2 = new JLabel("平板数据", JLabel.CENTER);
        panel2.add(label2, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.WEST);*/

        /*JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        panel3.add(toolBar, BorderLayout.NORTH);
        panel3.add(canvas, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.CENTER);*/
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect=ge.getMaximumWindowBounds();
        int w=rect.width;
        int h=rect.height;

        MySplitPane splitPane1 = new MySplitPane();
        splitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane1.setContinuousLayout(true);
        splitPane1.setDividerLocation(0.1);
        JPanel jp3 = new JPanel();
        jp3.setBackground(Color.green);
        JPanel jp4 = new JPanel();
        jp4.setBackground(Color.blue);
        splitPane1.setRightComponent(jp3);
        splitPane1.setLeftComponent(jp4);

        JSplitPane splitPane = new MySplitPane();
        splitPane.setDividerSize(2);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerLocation((int) (w * 0.2));
        JPanel jp2 = new JPanel();
        jp2.setBackground(Color.yellow);
        splitPane.setRightComponent(splitPane1);
        splitPane.setLeftComponent(jp2);
        this.add(splitPane, BorderLayout.CENTER);

        /*JPanel panel4 = new JPanel(new BorderLayout());
        panel4.setPreferredSize(new Dimension(200, 40));
        panel4.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label4 = new JLabel("map", JLabel.CENTER);
        panel4.add(label4, BorderLayout.CENTER);
        this.add(panel4,BorderLayout.SOUTH);*/
    }

    public static void main(String args[]){

        try{
            //设置本属性将改变窗口边框样式定义
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestSplitPane test = new TestSplitPane();
    }
}
