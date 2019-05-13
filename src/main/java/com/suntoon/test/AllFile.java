package com.suntoon.test;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

/**
 * @ProjectionName desktop
 * @ClassName AllFile
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/8 0008下午 3:17
 * @Version 1.0
 */
public class AllFile extends JFrame {

    private static final long serialVersionUID = -1877527354792619586L;

    static JTree tree;
    static DefaultTreeModel newModel;
    static DefaultMutableTreeNode Node;
    static DefaultMutableTreeNode temp;

    static String path = "E:\\ArcGISdata";
    
    public AllFile() {
        Node = traverseFolder(path);
        newModel = new DefaultTreeModel(Node);
        tree = new JTree(newModel);

        this.setSize(400, 300);
        this.add(new JScrollPane(tree));
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        try{
            //设置本属性将改变窗口边框样式定义
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new AllFile();
    }

    private DefaultMutableTreeNode traverseFolder(String path) {
        DefaultMutableTreeNode fujiedian = new DefaultMutableTreeNode(new File(path).getName());
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                if (file.isDirectory()) {    //如果是空文件夹
                    DefaultMutableTreeNode dn = new DefaultMutableTreeNode(file.getName(), false);
                    return dn;
                }
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //是目录的话，生成节点，并添加里面的节点
                        fujiedian.add(traverseFolder(file2.getAbsolutePath()));
                    } else {
                        //是文件的话直接生成节点，并把节点加到对应的父节点上
                        temp = new DefaultMutableTreeNode(file2.getName());
                        fujiedian.add(temp);
                    }
                }
            }
        } else {    //文件不存在
            return null;
        }

        return fujiedian;
    }
}
