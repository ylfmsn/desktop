package com.suntoon.map.action;

import com.suntoon.map.MapPane;
import org.geotools.data.CachingFeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Random;

/**
 * @ProjectionName desktop
 * @ClassName OpenShpLayerAction
 * @Description 导入shp文件的操作
 * @Author YueLifeng
 * @Date 2019/4/29 0029下午 5:09
 * @Version 1.0
 */
public class OpenShpLayerAction extends AbstractMapAction {

    private static final long serialVersionUID = 1L;

    //导入操作
    public OpenShpLayerAction(MapPane mapPane) {
        super(mapPane);
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/map/mOpenLayer.png")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "导入shp文件");
    }

    //生成随机颜色的种子
    private static Random rd = new Random();

    @Override
    public void actionPerformed(ActionEvent e) {

        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory() || (f.isFile() && f.getName().endsWith(".shp")))
                    return true;

                return false;
            }

            @Override
            public String getDescription() {
                return "shpFile";
            }
        });

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            return;

        File file = fileChooser.getSelectedFile();

        if (file == null)
            return;

        try {
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource featureSource = store.getFeatureSource();
            CachingFeatureSource cache = new CachingFeatureSource(featureSource);
            Style style = SLD.createSimpleStyle(featureSource.getSchema(),
                    new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
            FeatureLayer layer = new FeatureLayer(cache, style, file.getName().substring(0, file.getName().lastIndexOf(".")));

            this.getMapPane().getMapContent().addLayer(layer);
            this.getMapPane().repaint(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
