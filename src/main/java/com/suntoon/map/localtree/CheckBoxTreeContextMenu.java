package com.suntoon.map.localtree;

import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.Style;
import org.geotools.swing.styling.JSimpleStyleDialog;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName CheckBoxTreeContextMenu
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/14 0014下午 3:27
 * @Version 1.0
 */
public class CheckBoxTreeContextMenu extends JPopupMenu {

    private Clipboard clipboard;

    private JMenuItem remove;
    private JMenuItem style;

    private CheckBoxTreeNode node;
    private JMapTree tree;

    public CheckBoxTreeContextMenu(JMapTree tree, CheckBoxTreeNode node) {
        this.node = node;
        this.tree = tree;

        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        addPopupMenuItems();
    }

    private void addPopupMenuItems() {

        remove = new JMenuItem("移除   ");
        remove.setEnabled(true);
        //remove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                MapContent mapContent = tree.getMapPane().getMapContent();
                if (node.getParent() != null) {
                    if (!node.isLeaf()) {

                        int childNum = node.getChildCount();
                        for (int i = 0; i < childNum; i++) {
                            Map childMap = (Map) ((CheckBoxTreeNode) (node.getChildAt(i))).getTreeAttribute();
                            mapContent.removeLayer((Layer) childMap.get("layer"));
                        }
                        model.removeNodeFromParent(node);
                    } else {

                        CheckBoxTreeNode parentNode = (CheckBoxTreeNode) node.getParent();

                        Map childMap = (Map) node.getTreeAttribute();
                        mapContent.removeLayer((Layer) childMap.get("layer"));
                        model.removeNodeFromParent(node);

                        if (parentNode.getChildCount() == 0)
                            model.removeNodeFromParent(parentNode);
                    }
                }
            }
        });
        add(remove);
        addSeparator();

        style = new JMenuItem("样式   ");
        style.setEnabled(true);
        style.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (node.isLeaf()) {
                    Map childMap = (Map) node.getTreeAttribute();
                    Layer layer = (Layer) childMap.get("layer");
                    FeatureLayer styleLayer = (FeatureLayer) layer;
                    Style style = JSimpleStyleDialog.showDialog(null, styleLayer);
                    if (style != null) {
                        styleLayer.setStyle(style);
                    }
                }
            }
        });
        add(style);
    }


}
