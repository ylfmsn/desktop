package com.suntoon.map.oldaction;

import com.suntoon.map.MapPane;
import org.geotools.coverage.GridSampleDimension;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.Hints;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.Layer;
import org.geotools.styling.*;
import org.opengis.filter.FilterFactory2;
import org.opengis.style.ContrastMethod;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * @ProjectionName desktop
 * @ClassName OpenRasterLayerAction
 * @Description 导入raster文件的操作
 * @Author YueLifeng
 * @Date 2019/5/5 0005下午 5:12
 * @Version 1.0
 */
public class OpenRasterLayerAction extends AbstractMapAction {

    private static final long serialVersionUID = 1L;

    private GridCoverage2DReader reader;

    private StyleFactory sf = CommonFactoryFinder.getStyleFactory();
    private FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

    /**
     * @Author YueLifeng
     * @Description //导入操作
     * @Date 下午 5:21 2019/5/5 0005
     * @param mapPane
     * @return
     */
    public OpenRasterLayerAction(MapPane mapPane) {
        super(mapPane);
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/map/mOpenLayer.png")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "导入栅格文件");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return "raster";
            }

            @Override
            public boolean accept(File f) {
                if (f.isDirectory() || (f.isFile() && f.getName().endsWith(".tif")))
                    return true;

                return false;
            }
        });
        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            return;

        File file = fileChooser.getSelectedFile();

        if (file == null) {
            return;
        }

        try {
            AbstractGridFormat format = GridFormatFinder.findFormat(file);
            //this is a bit hacky but does make more geotiffs work
            Hints hints = new Hints();
            if (format instanceof GeoTiffFormat) {
                hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
            }
            reader = format.getReader(file, hints);

            Style rasterStyle = createRGBStyle();
            Layer layer = new GridReaderLayer(reader, rasterStyle);
            this.getMapPane().getMapContent().addLayer(layer);
            this.getMapPane().repaint(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Style createRGBStyle() {
        GridCoverage2D cov = null;
        try {
            cov = reader.read(null);
        } catch (IOException giveUp) {
            throw new RuntimeException(giveUp);
        }
        // We need at least three bands to create an RGB style
        int numBands = cov.getNumSampleDimensions();
        if (numBands < 3) {
            return null;
        }
        // Get the names of the bands
        String[] sampleDimensionNames = new String[numBands];
        for (int i = 0; i < numBands; i++) {
            GridSampleDimension dim = cov.getSampleDimension(i);
            sampleDimensionNames[i] = dim.getDescription().toString();
        }
        final int RED = 0, GREEN = 1, BLUE = 2;
        int[] channelNum = { -1, -1, -1 };
        // We examine the band names looking for "red...", "green...", "blue...".
        // Note that the channel numbers we record are indexed from 1, not 0.
        for (int i = 0; i < numBands; i++) {
            String name = sampleDimensionNames[i].toLowerCase();
            if (name != null) {
                if (name.matches("red.*")) {
                    channelNum[RED] = i + 1;
                } else if (name.matches("green.*")) {
                    channelNum[GREEN] = i + 1;
                } else if (name.matches("blue.*")) {
                    channelNum[BLUE] = i + 1;
                }
            }
        }
        // If we didn't find named bands "red...", "green...", "blue..."
        // we fall back to using the first three bands in order
        if (channelNum[RED] < 0 || channelNum[GREEN] < 0 || channelNum[BLUE] < 0) {
            channelNum[RED] = 1;
            channelNum[GREEN] = 2;
            channelNum[BLUE] = 3;
        }
        // Now we create a RasterSymbolizer using the selected channels
        SelectedChannelType[] sct = new SelectedChannelType[cov.getNumSampleDimensions()];
        ContrastEnhancement ce = sf.contrastEnhancement(ff.literal(1.0), ContrastMethod.NORMALIZE);
        for (int i = 0; i < 3; i++) {
            sct[i] = sf.createSelectedChannelType(String.valueOf(channelNum[i]), ce);
        }
        RasterSymbolizer sym = sf.getDefaultRasterSymbolizer();
        ChannelSelection sel = sf.channelSelection(sct[RED], sct[GREEN], sct[BLUE]);
        sym.setChannelSelection(sel);

        return SLD.wrapSymbolizers(sym);
    }
}
