package com.suntoon.map.oldaction;

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
import java.io.IOException;

/**
 * @ProjectionName desktop
 * @ClassName OpenShpLayerAction
 * @Description 导入shp文件的操作
 * @Author YueLifeng
 * @Date 2019/4/29 0029下午 5:09
 * @Version 1.0
 */
public class OpenLayerAction extends MapAction {

    private static final long serialVersionUID = 1L;

    private JMapPane mapPane;

    private JMapTree mapTree;

    public JMapTree getMapTree() {
        return mapTree;
    }

    public void setMapTree(JMapTree mapTree) {
        this.mapTree = mapTree;
    }

    public JMapPane getMapPane() {
        return this.mapPane;
    }

    public void setMapPane(JMapPane mapPane) {
        this.mapPane = mapPane;
    }

    //导入操作
    public OpenLayerAction(JMapPane mapPane, JMapTree mapTree) {
        ImageIcon img = new ImageIcon(this.getClass().getResource("/map/mOpenLayer1.png"));
        img.setImage(img.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        this.putValue(SMALL_ICON, img);
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "导入GIS数据");
        setMapPane(mapPane);
        this.mapTree = mapTree;
        this.mapTree.setMapPane(mapPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*List<Parameter<?>> list = new ArrayList<>();
        list.add(new Parameter<>("image", File.class, "Image",
                "GeoTiff or World+Image to display as basemap",
                new KVP(Parameter.EXT, "tif", Parameter.EXT, "jpg")));
        list.add(new Parameter<>("shape", File.class, "Shapefile", "Shapefile contents to display",
                new KVP(Parameter.EXT, "shp")));

        JParameterListWizard wizard = new JParameterListWizard("加载数据",
                "Fill in the following layers", list);

        int finish = wizard.showModalDialog();

        if (finish != JWizard.FINISH) {
            return;
        }
        File imageFile = (File) wizard.getConnectionParameters().get("image");
        File shapeFile = (File) wizard.getConnectionParameters().get("shape");
        if (imageFile == null || shapeFile == null)
            return;
        try {
            getMapPane().getMapContent().addLayer(new JMapPanel().displayShpLayers(shapeFile));
            getMapPane().getMapContent().addLayer(new JMapPanel().displayRasterLayers(imageFile));
        } catch (IOException e1) {
            e1.printStackTrace();
        }*/
        /*JFileDataStoreChooser fileDataStoreChooser = new JFileDataStoreChooser(new String[]{"tif", "tiff", "jpg", "shp"});
        File file = JFileDataStoreChooser.showOpenFile(new String[]{"tif", "tiff", "jpg", "shp"}, null);
        fileDataStoreChooser.setMultiSelectionEnabled(true);*/

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("tif; tiff; jpg; shp", "tif", "tiff", "jpg", "shp");
        fileChooser.setFileFilter(filter);
        fileChooser.setMultiSelectionEnabled(true);
        int finish = fileChooser.showOpenDialog(new Label("选择数据"));
        File[] files = fileChooser.getSelectedFiles();

        if (finish == JFileChooser.APPROVE_OPTION) {
            if (files != null) {
                for (File file : files) {

                    String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                    try {
                        if ("shp".equals(ext)) {
                            Layer layer = new JMapPanel().displayShpLayers(file);
                            getMapPane().getMapContent().addLayer(layer);
                            mapTree.addTreeNode(file.getAbsolutePath(), layer);
                        } else if ("tif".equals(ext) || "tiff".equals(ext) || "jpg".equals(ext)) {
                            Layer layer = new JMapPanel().displayRasterLayers(file);
                            getMapPane().getMapContent().addLayer(layer);
                            mapTree.addTreeNode(file.getAbsolutePath(), layer);
                        } else {
                            JOptionPane.showMessageDialog(null, "您加载的数据格式不正确，请重新选择加载数据！", "加载数据提示框", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } else {
                return;
            }
        } else {
            return;
        }

        mapTree.setCellRenderer(new CheckBoxTreeCellRenderer());
    }
}
