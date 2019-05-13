package com.suntoon.checkboxtree;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName Demo
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/9 0009上午 10:10
 * @Version 1.0
 */
public class Demo extends JFrame {
    private static final long serialVersionUID = 4648172894076113183L;

    public Demo() {
        super();
        setSize(500, 500);
        this.getContentPane().setLayout(new BorderLayout());
        final JCheckBoxTree cbt = new JCheckBoxTree();
        this.getContentPane().add(cbt);
        cbt.addCheckChangeEventListener(new JCheckBoxTree.CheckChangeEventListener() {
            public void checkStateChanged(JCheckBoxTree.CheckChangeEvent event) {
                System.out.println("event");
                TreePath[] paths = cbt.getCheckedPaths();
                for (TreePath tp : paths) {
                    for (Object pathPart : tp.getPath()) {
                        System.out.print(pathPart + ",");
                    }
                    System.out.println();
                }
            }
        });
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {

        try{
            //设置本属性将改变窗口边框样式定义
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Demo m = new Demo();
        m.setVisible(true);
    }
}
