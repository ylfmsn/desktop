package com.suntoon.map.action;

import com.suntoon.map.JMapPanel;
import com.suntoon.map.localtree.CheckBoxTreeCellRenderer;
import com.suntoon.map.localtree.JMapTree;
import org.geotools.map.Layer;
import org.geotools.swing.JMapPane;
import org.geotools.swing.action.MapAction;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * @ProjectionName desktop
 * @ClassName OpenLayerAction
 * @Description 加载数据
 * @Author YueLifeng
 * @Date 2019/5/14 0014下午 6:23
 * @Version 1.0
 */
public class OpenLayerAction extends MapAction {

    private static final long serivalVersionUID = 1L;

    private JMapPane mapPane;

    private JMapTree mapTree;

    //导入数据
    public OpenLayerAction(JMapPane mapPane, JMapTree mapTree) {

        ImageIcon img = new ImageIcon(this.getClass().getResource("/map/mOpenlayer1.png"));
        img.setImage(img.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        this.putValue(SMALL_ICON, img);
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "加载数据");
        this.setMapPane(mapPane);
        this.setMapTree(mapTree);
        this.mapTree.setMapPane(mapPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("tif; tiff; jpg; shp", "tif", "tiff",
                "jpg", "shp");
        fileChooser.setFileFilter(filter);
        fileChooser.setMultiSelectionEnabled(true);
        int finish = fileChooser.showOpenDialog(new Label("选择数据"));
        File[] files = fileChooser.getSelectedFiles();

        if (finish == JFileChooser.APPROVE_OPTION) {

            JMapPanel jMapPanel = new JMapPanel();

            if (files != null) {

                for (File file : files) {

                    String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                    try {
                        if ("shp".equals(ext)) {
                            Layer layer = jMapPanel.displayShpLayers(file);
                            getMapPane().getMapContent().addLayer(layer);
                            mapTree.addTreeNode(file.getAbsolutePath(), layer);
                        } else if ("tif".equals(ext) || "tiff".equals(ext) || "jpg".equals(ext)) {
                            Layer layer = jMapPanel.displayRasterLayers(file);
                            getMapPane().getMapContent().addLayer(layer);
                            mapTree.addTreeNode(file.getAbsolutePath(), layer);
                        } else {
                            JOptionPane.showMessageDialog(null, "您加载的数据格式不正确，请重新选择加载数据！", "加载数据提示框", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                //节点加载完毕后重新加载树渲染器
                mapTree.setCellRenderer(new CheckBoxTreeCellRenderer());
            } else {
                return;
            }
        } else {
            return;
        }
    }

    public JMapPane getMapPane() {
        return mapPane;
    }

    public void setMapPane(JMapPane mapPane) {
        this.mapPane = mapPane;
    }

    public JMapTree getMapTree() {
        return mapTree;
    }

    public void setMapTree(JMapTree mapTree) {
        this.mapTree = mapTree;
    }
}
