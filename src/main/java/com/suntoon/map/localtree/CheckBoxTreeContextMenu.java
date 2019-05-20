package com.suntoon.map.localtree;

import com.suntoon.map.style.JSimpleStyleDialog;
import com.suntoon.map.table.MapTableFrame;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.map.MapViewport;
import org.geotools.styling.Style;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

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
    private JMenuItem extent;
    private JMenuItem attribute;

    private CheckBoxTreeNode node;
    private JMapTree tree;

    public CheckBoxTreeContextMenu(JMapTree tree, CheckBoxTreeNode node) {
        this.node = node;
        this.tree = tree;

        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        addPopupMenuItems();
    }

    private void addPopupMenuItems() {

        remove = new JMenuItem("移除图层   ");
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

        style = new JMenuItem("样式设置   ");
        style.setEnabled(true);
        style.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (node.isLeaf()) {
                    Map childMap = (Map) node.getTreeAttribute();
                    Layer layer = (Layer) childMap.get("layer");
                    FeatureLayer styleLayer = (FeatureLayer) layer;
                    Style style = JSimpleStyleDialog.showDialog(tree.getMapPane(), styleLayer);
                    if (style != null) {
                        styleLayer.setStyle(style);
                    }
                }
            }
        });
        add(style);
        addSeparator();

        attribute = new JMenuItem("属性查看   ");
        attribute.setEnabled(true);
        attribute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (node.isLeaf()) {
                    Map childMap = (Map) node.getTreeAttribute();
                    String file = (String) childMap.get("path");
                    System.out.println(file);
                    file = file.substring(0, file.lastIndexOf(".")) + ".dbf";
                    System.out.println(file);
                    MapTableFrame mapTableFrame = new MapTableFrame(file);
                }
            }
        });
        add(attribute);
        addSeparator();

        // bug 功能未实现
        extent = new JMenuItem("全局查看   ");
        extent.setEnabled(true);
        extent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapContent mapContent = tree.getMapPane().getMapContent();
                if (node.isLeaf()) {

                    Map childMap = (Map) node.getTreeAttribute();
                    Layer layer = (Layer) childMap.get("layer");
                    ReferencedEnvelope envelope = layer.getBounds();
                    CoordinateReferenceSystem crs = envelope.getCoordinateReferenceSystem();
                    MapViewport viewport = new MapViewport(envelope);
                    viewport.setCoordinateReferenceSystem(crs);
                    mapContent.setViewport(viewport);
                }
            }
        });
        add(extent);
    }
}
