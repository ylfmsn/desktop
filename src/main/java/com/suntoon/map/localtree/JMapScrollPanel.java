package com.suntoon.map.localtree;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName JMapScrollPanel
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/13 0013下午 2:37
 * @Version 1.0
 */
public class JMapScrollPanel extends JScrollPane {

    private JTree tree;

    private CheckBoxTreeNode rootNode;

    public JMapScrollPanel () {
        super();
        tree = this.initJTree();

    }

    private JTree initJTree() {

        JTree tree = new JTree();

        //添加根节点
        Map rootMap = new HashMap<>();
        rootMap.put("path", "图层");
        rootMap.put("name", "图层");
        rootNode = new CheckBoxTreeNode(rootMap);
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        tree.addMouseListener(new CheckBoxTreeNodeSelectionListener() {

        });
        tree.setModel(model);
        tree.setCellRenderer(new CheckBoxTreeCellRenderer());
        return tree;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("桌面端树状数据测试");
        frame.setBounds(200, 200, 300, 320);
        frame.add(new JMapScrollPanel(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
