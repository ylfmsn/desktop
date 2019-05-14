package com.suntoon.map.localtree;

import org.geotools.map.Layer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        if (SwingUtilities.isLeftMouseButton(e)) {
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

                    if (!node.isLeaf() && node.isSelected()) {    //选中目录，遍历子文件夹，加载文件夹中的图层数据

                        int childCount = node.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            CheckBoxTreeNode cbtn = (CheckBoxTreeNode) node.getChildAt(i);
                            if (!cbtn.isLeaf()) {     //文件夹, 选中root节点的情况下
                                int childNum = cbtn.getChildCount();
                                for (int m = 0; m < childNum; m++) {
                                    CheckBoxTreeNode cbt = (CheckBoxTreeNode) cbtn.getChildAt(m);

                                    Map dataMap = (Map) cbt.getTreeAttribute();
                                    Layer layer = (Layer) dataMap.get("layer");
                                    layer.setVisible(true);
                                }
                            } else {    //选中root下文件夹的情况
                                Map dataMap = (Map) cbtn.getTreeAttribute();
                                Layer layer = (Layer) dataMap.get("layer");
                                layer.setVisible(true);
                            }
                        }
                    } else if (node.isLeaf() && node.isSelected() && node.getParent() != null){
                        Map dataMap = (Map) node.getTreeAttribute();
                        Layer layer = (Layer) dataMap.get("layer");
                        layer.setVisible(true);
                    } else if (!node.isLeaf() && !node.isSelected()) {
                        int childCount = node.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            CheckBoxTreeNode cbtn = (CheckBoxTreeNode) node.getChildAt(i);
                            if (!cbtn.isLeaf()) {     //文件夹, 取消选中root节点的情况下
                                int childNum = cbtn.getChildCount();
                                for (int m = 0; m < childNum; m++) {
                                    CheckBoxTreeNode cbt = (CheckBoxTreeNode) cbtn.getChildAt(m);

                                    Map dataMap = (Map) cbt.getTreeAttribute();
                                    Layer layer = (Layer) dataMap.get("layer");
                                    layer.setVisible(false);
                                }
                            } else {    //取消选中root下文件夹的情况
                                Map dataMap = (Map) cbtn.getTreeAttribute();
                                Layer layer = (Layer) dataMap.get("layer");
                                layer.setVisible(false);
                            }
                        }
                    } else if (node.isLeaf() && !node.isSelected() && node.getParent() != null){
                        Map dataMap = (Map) node.getTreeAttribute();
                        Layer layer = (Layer) dataMap.get("layer");
                        layer.setVisible(false);
                    }
                }
            }

        } else if (SwingUtilities.isRightMouseButton(e)) {
            JMapTree mapTree = (JMapTree) e.getSource();
            int row = mapTree.getClosestRowForLocation(e.getX(), e.getY());
            mapTree.setSelectionRow(row);
            TreePath path = mapTree.getPathForRow(row);
            if (path != null) {
                CheckBoxTreeNode node = (CheckBoxTreeNode) path.getLastPathComponent();
                CheckBoxTreeContextMenu popupMenu = new CheckBoxTreeContextMenu(mapTree, node);
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }









    }
}
