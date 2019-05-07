package com.suntoon.test;

import com.suntoon.map.JMapPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @ProjectionName desktop
 * @ClassName TestMapPanel
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/7 0007下午 3:25
 * @Version 1.0
 */
public class TestMapPanel {

    private static Random rd = new Random();

    public static void main(String[] args) throws IOException {

        File rasterFile = new File("E:\\ArcGISdata\\binlinqu\\L17-prj.tif");
        File shpFile1 = new File("E:\\ArcGISdata\\bou4_4m\\BOUNT_line.shp");
        File shpFile2 = new File("E:\\ArcGISdata\\bou4_4m\\BOUNT_poly.shp");

        JFrame frame = new JFrame();
        frame.setSize(1224, 840);
        JMapPanel mapPanel = new JMapPanel();
        frame.add(mapPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //mapPanel.getMapContent().addLayer(mapPanel.displayShpLayers(shpFile1));
        //mapPanel.getMapContent().addLayer(mapPanel.displayRasterLayers(rasterFile));
        //mapPanel.getMapContent().addLayer(mapPanel.displayShpLayers(shpFile1));

        //System.out.println(mapPanel.getMapContent().layers().get(0));
        //mapPanel.getMapContent().layers().get(0).setVisible(false);
        mapPanel.repaint(mapPanel.getMapContent().getViewport().getScreenArea());

        frame.setVisible(true);
    }
}
