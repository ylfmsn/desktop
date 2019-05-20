package com.suntoon.test.table;

import javax.swing.*;

/**
 * 演示代码
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
    		//获取当前操作系统的主题
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()	);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		JFrameTableDemo frm = new JFrameTableDemo();
    	frm.setSize(1024 , 768);
    	frm.setLocationRelativeTo(null); //在屏幕上居中
        frm.setVisible(true);
    }
}
