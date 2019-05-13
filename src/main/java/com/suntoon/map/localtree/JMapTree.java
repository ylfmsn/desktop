package com.suntoon.map.localtree;

import org.geotools.data.Parameter;
import org.geotools.swing.data.JParameterListWizard;
import org.geotools.swing.wizard.JWizard;
import org.geotools.util.KVP;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
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
 * @ClassName JMapTree
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/13 0013下午 3:03
 * @Version 1.0
 */
public class JMapTree extends JTree {

    private CheckBoxTreeNode rootNode;

    public JMapTree() {
        super();
        initCompenent();
    }

    private void initCompenent() {
        //添加根节点
        Map rootMap = new HashMap<>();
        rootMap.put("path", "图层");
        rootMap.put("name", "图层");
        rootNode = new CheckBoxTreeNode(rootMap);
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        this.addMouseListener(new CheckBoxTreeNodeSelectionListener() {

        });
        this.setModel(model);
        this.setCellRenderer(new CheckBoxTreeCellRenderer());
    }

    public void addTreeNode(String path) {

        int count = rootNode.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                CheckBoxTreeNode childNode = (CheckBoxTreeNode) rootNode.getChildAt(i);
                Map map = (Map) childNode.getTreeAttribute();
                String rootPath = map.get("path").toString();

                if (path.substring(0, path.lastIndexOf("\\")).equals(rootPath)) {
                    Map rootMap2 = new HashMap();
                    rootMap2.put("path", path);
                    rootMap2.put("name", path.substring(path.lastIndexOf("\\") + 1));
                    CheckBoxTreeNode childTreeNode2 = new CheckBoxTreeNode(rootMap2);
                    //childTreeNode2.setSelected(true);
                    childNode.add(childTreeNode2);

                    DefaultTreeModel model = new DefaultTreeModel(rootNode);
                    this.addMouseListener(new CheckBoxTreeNodeSelectionListener() {

                    });
                    this.setModel(model);
                    this.setCellRenderer(new CheckBoxTreeCellRenderer());

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
        //childTreeNode1.setSelected(true);
        rootNode.add(childTreeNode);

        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        this.addMouseListener(new CheckBoxTreeNodeSelectionListener() {

        });
        this.setModel(model);
        this.setCellRenderer(new CheckBoxTreeCellRenderer());
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
                if (file.isDirectory()) {    //如果是空文件夹
                    Map<String, String> map1 = new HashMap<>();
                    map1.put("path", file.getAbsolutePath());
                    map1.put("name", file.getName());
                    CheckBoxTreeNode dn = new CheckBoxTreeNode(map1);
                    return dn;
                }
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

    public static void main(String[] args) {

        JMapTree tree = new JMapTree();

        JFrame frame = new JFrame("桌面端树状数据测试");
        frame.setBounds(200, 200, 300, 320);
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

                tree.addTreeNode(imageStr);
                tree.addTreeNode(shapeStr);
            }

        });

        frame.add(btn, BorderLayout.NORTH);
        //frame.getContentPane().add(new JButton("add data"));
        //frame.getContentPane().add(scroll);
        frame.add(new JScrollPane(tree), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public CheckBoxTreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(CheckBoxTreeNode rootNode) {
        this.rootNode = rootNode;
    }

}
