package com.suntoon.treedemo;

import org.geotools.data.Parameter;
import org.geotools.swing.data.JParameterListWizard;
import org.geotools.swing.wizard.JWizard;
import org.geotools.util.KVP;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName Demo
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/8 0008下午 5:43
 * @Version 1.0
 */
public class Demo {

    private JTree tree = new JTree();

    private CheckBoxTreeNode rootNode;

    public static void main(String[] args) {

        Demo demo = new Demo();

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

        String path = "E:\\ArcGISdata\\lls";
        JFrame frame = new JFrame("桌面端树状数据测试");
        frame.setBounds(200, 200, 300, 320);

        //JScrollPane scroll = boxTreePanel(path);
        JScrollPane scroll = demo.layerScrollTree();

        scroll.setBounds(0, 0, 300, 320);

        JButton btn = new JButton("add data");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Parameter<?>> list = new ArrayList<>();
                list.add(new Parameter<>("image", File.class, "Image",
                        "GeoTiff or World+Image to display as basemap",
                        new KVP(Parameter.EXT, "tif", Parameter.EXT, "jpg")));
                list.add(new Parameter<>("shape", File.class, "Shapefile", "Shapefile contents to display",
                        new KVP(Parameter.EXT, "shp")));

                JParameterListWizard wizard = new JParameterListWizard("Image Lab",
                        "Fill in the following layers", list);

                int finish = wizard.showModalDialog();

                if (finish != JWizard.FINISH) {
                    return;
                }
                File imageFile = (File) wizard.getConnectionParameters().get("image");
                File shapeFile = (File) wizard.getConnectionParameters().get("shape");
                String imageStr = imageFile.getAbsolutePath();
                String shapeStr = shapeFile.getAbsolutePath();

                demo.addTreeNode(imageStr);
                demo.addTreeNode(shapeStr);
            }

        });

        frame.add(btn, BorderLayout.NORTH);
        //frame.getContentPane().add(new JButton("add data"));
        //frame.getContentPane().add(scroll);
        frame.add(scroll, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JScrollPane layerScrollTree() {

        Map rootMap = new HashMap();
        rootMap.put("path", "图层");
        rootMap.put("name", "图层");
        rootNode = new CheckBoxTreeNode(rootMap);
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        tree.addMouseListener(new CheckBoxTreeNodeSelectionListener() {

        });
        tree.setModel(model);
        tree.setCellRenderer(new CheckBoxTreeCellRenderer());
        JScrollPane scrollPane = new JScrollPane(tree);

        return scrollPane;
    }

    public void addTreeNode(String path) {

        TreeNode[] treeNodes = rootNode.getPath();
        int count = rootNode.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                CheckBoxTreeNode childNode = (CheckBoxTreeNode) rootNode.getChildAt(i);
                Map map = (Map) childNode.getAttr();
                String rootPath = map.get("path").toString();
                System.out.println(rootPath);
                if (path.substring(0, path.lastIndexOf("\\")).equals(rootPath)) {
                    Map rootMap2 = new HashMap();
                    rootMap2.put("path", path);
                    rootMap2.put("name", path.substring(path.lastIndexOf("\\") + 1));
                    CheckBoxTreeNode childTreeNode2 = new CheckBoxTreeNode(rootMap2);
                    childNode.add(childTreeNode2);

                    DefaultTreeModel model = new DefaultTreeModel(rootNode);
                    tree.addMouseListener(new CheckBoxTreeNodeSelectionListener() {

                    });
                    tree.setModel(model);
                    tree.setCellRenderer(new CheckBoxTreeCellRenderer());

                    return;
                }
            }
        }

        Map rootMap = new HashMap();
        rootMap.put("path", path.substring(0, path.lastIndexOf("\\")));
        rootMap.put("name", path.substring(0, path.lastIndexOf("\\")));
        CheckBoxTreeNode childTreeNode = new CheckBoxTreeNode(rootMap);

        Map rootMap1 = new HashMap();
        rootMap1.put("path", path);
        rootMap1.put("name", path.substring(path.lastIndexOf("\\") + 1));
        CheckBoxTreeNode childTreeNode1 = new CheckBoxTreeNode(rootMap1);

        childTreeNode.add(childTreeNode1);
        rootNode.add(childTreeNode);

        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        tree.addMouseListener(new CheckBoxTreeNodeSelectionListener() {

        });
        tree.setModel(model);
        tree.setCellRenderer(new CheckBoxTreeCellRenderer());
    }

    public static JScrollPane boxTreePanel(String path) {

        JTree tree = new JTree();
        CheckBoxTreeNode rootNode = traverseFolder(path);
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        tree.addMouseListener(new CheckBoxTreeNodeSelectionListener() {

        });
        tree.setModel(model);
        tree.setCellRenderer(new CheckBoxTreeCellRenderer());
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setMaximumSize(new Dimension(300, 320));
        scrollPane.setMinimumSize(new Dimension(300, 320));
        scrollPane.setBounds(0, 0, 300, 320);

        return scrollPane;
    }

    private static CheckBoxTreeNode traverseFolder(String path) {
        Map<String, String> map = new HashMap<>();
        map.put("path", new File(path).getAbsolutePath());
        map.put("name", new File(path).getName());
        CheckBoxTreeNode fujiedian = new CheckBoxTreeNode(map);

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                /*if (file.isDirectory()) {    //如果是空文件夹
                    Map<String, String> map1 = new HashMap<>();
                    map1.put("path", file.getAbsolutePath());
                    map1.put("name", file.getName());
                    CheckBoxTreeNode dn = new CheckBoxTreeNode(map1);
                    return dn;
                }*/
            } else {
                for (File file2 : files) {
                    Map<String, String> map2 = new HashMap<>();
                    map2.put("path", file2.getAbsolutePath());
                    map2.put("name", file2.getName());
                    if (file2.isDirectory()) {
                        //是目录的话，生成节点，并添加里面的节点
                        fujiedian.add(traverseFolder(map2.get("path")));
                    } else {
                        String ext = file2.getName().substring(file2.getName().lastIndexOf(".") + 1);
                        if ("shp".equals(ext) || "tif".equals(ext) || "jpg".equals(ext) || "png".equals(ext)) {
                            //是文件的话直接生成节点，并把节点加到对应的父节点上
                            CheckBoxTreeNode temp = new CheckBoxTreeNode(map2);
                            fujiedian.add(temp);
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {    //文件不存在
            return null;
        }

        return fujiedian;
    }

    //判断目录下子目录是否有shp或者tif数据
    private static boolean isValidateData(String path) {
        boolean result = false;

        File file = new File(path);
        File[] files = file.listFiles();

        if (files.length > 0) {     // 非空目录
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    result = true;
                    return result;
                } else {
                    String ext = file1.getName().substring(file1.getName().lastIndexOf(".") + 1);
                    if ("shp".equals(ext) || ("tif").equals(ext)) {
                        result = true;
                        return result;
                    }
                }
            }
        }

        return result;
    }
}
