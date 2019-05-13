package com.suntoon.treedemo;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName CheckBoxTreeNodeSelectionListener
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/8 0008下午 5:38
 * @Version 1.0
 */
public class CheckBoxTreeNodeSelectionListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        JTree tree = (JTree) e.getSource();
        int x = e.getX();
        int y = e.getY();
        int row = tree.getRowForLocation(x, y);
        TreePath path = tree.getPathForRow(row);
        if (path != null) {
            CheckBoxTreeNode node = (CheckBoxTreeNode) path.getLastPathComponent();
            if (node != null) {
                boolean isSelected = !node.isSelected();
                node.setSelected(isSelected);
                ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
                if (node.isSelected()) {
                    System.out.println("--------------->" + node.toString());
                    txtForAllNode(node);
                }
            }
        }
    }


    private static void txtForAllNode(CheckBoxTreeNode node) {

        if (node.isSelected()) {
            int childCount = node.getChildCount();

            for (int i = 0; i < childCount; i++) {

                CheckBoxTreeNode childNode = (CheckBoxTreeNode) node.getChildAt(i);

                if (childNode.isSelected()) {

                    if (childNode.getChildCount() == 0) {     //文件或者空文件件
                        Map map = (Map) childNode.getAttr();
                        String path = map.get("path").toString();     //节点文件/文件件路径

                        File file = new File(path);
                        if (file.isDirectory()) {       //空文件夹
                            //不做任何处理
                        } else {
                            String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                            if ("shp".equals(ext)) {
                                System.out.println(map.get("name").toString());
                                System.out.println(map.get("path").toString());
                            }
                        }
                    } else {     //文件件
                        txtForAllNode(childNode);
                    }
                }

            }

        }
    }
}
