package com.suntoon.map.action;

import com.suntoon.map.JMapPanel;
import org.geotools.data.Parameter;
import org.geotools.swing.JMapPane;
import org.geotools.swing.action.MapAction;
import org.geotools.swing.data.JParameterListWizard;
import org.geotools.swing.wizard.JWizard;
import org.geotools.util.KVP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public JMapPane getMapPane() {
        return this.mapPane;
    }

    public void setMapPane(JMapPane mapPane) {
        this.mapPane = mapPane;
    }

    //导入操作
    public OpenLayerAction(JMapPane mapPane) {
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/map/mOpenLayer.png")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "导入GIS数据");
        setMapPane(mapPane);
    }

    //生成随机颜色的种子
    private static Random rd = new Random();

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
        try {
            getMapPane().getMapContent().addLayer(new JMapPanel().displayShpLayers(shapeFile));
            getMapPane().getMapContent().addLayer(new JMapPanel().displayRasterLayers(imageFile));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
