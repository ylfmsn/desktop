package com.suntoon.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame {
    public static void main(String[] args){
        MainFrame f=new MainFrame();
    }
    private void myInit(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设定窗体关闭后自动退出进程
        setSize(1200,840);//设定窗体的默认尺寸
        setVisible(true);//显示窗体\
        jSplitPane2.setDividerLocation(0.2);
        jSplitPane1.setDividerLocation(0.85);//设定分割面板的左右比例(这时候就生效了，如果放在setVisible(true)这据之前就不会有效果。)
        jSplitPane1.setDividerSize(3);
        jSplitPane2.setDividerSize(3);
        /*****初始化事件***/
        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e) {
                jSplitPane2.setDividerLocation(0.2);
                jSplitPane1.setDividerLocation(0.85);
            }
        });
        jSplitPane1.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e) {
                jSplitPane2.setDividerLocation(0.2);
                //jSplitPane1.setDividerLocation(0.85);
            }
        });
    }

    public MainFrame() {
        try {
            jbInit();
            myInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1200, 140));
        this.getContentPane().add(jPanel, BorderLayout.NORTH);

        this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jSplitPane2.add(jPanel3, JSplitPane.LEFT);
        jSplitPane2.add(jPanel4, JSplitPane.RIGHT);

        jSplitPane1.add(jSplitPane2, JSplitPane.LEFT);
        jSplitPane1.add(jPanel2, JSplitPane.RIGHT);
    }

    JSplitPane jSplitPane1 = new JSplitPane();
    JPanel jPanel2 = new JPanel();

    JSplitPane jSplitPane2 = new JSplitPane();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();


}