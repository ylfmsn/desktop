package com.suntoon.test;

import org.geotools.coverage.GridSampleDimension;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.*;
import org.geotools.swing.JMapPane;
import org.opengis.filter.FilterFactory2;
import org.opengis.style.ContrastMethod;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @ProjectionName desktop
 * @ClassName MapImagePanel
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/6 0006下午 2:22
 * @Version 1.0
 */
public class MapImagePanel extends JPanel {
    private static final long serialVersionUID = -7924289582676785758L;
    private File blueMarble = null;
    private File borderShape = null;
    private GridCoverage2DReader gridCoverage2DReader;
    private StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory();

    public StyleFactory getStyleFactory() {
        return styleFactory;
    }

    private FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

    public MapImagePanel() {
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.BLUE);
        this.setPreferredSize(new Dimension(720, 360));

        try {
            //this.setBlueMarble(new File(this.getClass().getResource("/gis/BlueMarbleNG_2004-12-01_rgb_720x360.tiff").toURI()));
            this.setBlueMarble(new File("E:\\ArcGISdata\\binlinqu\\L17-prj.tif"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            //this.setBorderShape(new File(this.getClass().getResource("/gis/world_borders/TM_WORLD_BORDERS-0.3.shp").toURI()));
            this.setBorderShape(new File("E:\\ArcGISdata\\bou4_4m\\BOUNT_line.shp"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            this.displayLayers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayLayers() throws Exception {
        AbstractGridFormat format = GridFormatFinder.findFormat(this.getBlueMarble());
        this.setGridCoverageReader(format.getReader(this.getBlueMarble()));

        Style rgbStyle = this.createRGBStyle();

        // connect to the shapefile
        FileDataStore dataStore = FileDataStoreFinder.getDataStore(this.getBorderShape());
        SimpleFeatureSource shapefileSource = dataStore.getFeatureSource();

        Style shpStyle = SLD.createPolygonStyle(Color.BLUE, null, 0.0f);

        final MapContent map = new MapContent();
        map.setTitle("LogMap");

        Layer rasterLayer = new GridReaderLayer(this.getGridCoverageReader(), rgbStyle);
        map.addLayer(rasterLayer);

        Layer shpLayer = new FeatureLayer(shapefileSource, shpStyle);
        map.addLayer(shpLayer);

        JMapPane mapPane = new JMapPane();
        mapPane.setMapContent(map);
        //mapPane.setDisplayArea(shapefileSource.getBounds());
        mapPane.setDisplayArea(this.getGridCoverageReader().getOriginalEnvelope());

        this.add(mapPane, BorderLayout.CENTER);
        mapPane.setMapContent(map);
        mapPane.setDisplayArea(shapefileSource.getBounds());
    }

// docs start create rgb style
    /**
     * This method examines the names of the sample dimensions in the provided
     * coverage looking for "red...", "green..." and "blue..." (case insensitive
     * match). If these names are not found it uses bands 1, 2, and 3 for the
     * red, green and blue channels. It then sets up a raster symbolizer and
     * returns this wrapped in a Style.
     *
     * @return a new Style object containing a raster symbolizer set up for RGB
     *         image
     */
    private Style createRGBStyle() {
        GridCoverage2DReader reader = this.getGridCoverageReader();
        StyleFactory sf = this.getStyleFactory();
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
        // We examine the band names looking for "red...", "green...",
        // "blue...".
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
        if (channelNum[RED] < 0 || channelNum[GREEN] < 0
                || channelNum[BLUE] < 0) {
            channelNum[RED] = 1;
            channelNum[GREEN] = 2;
            channelNum[BLUE] = 3;
        }
        // Now we create a RasterSymbolizer using the selected channels
        SelectedChannelType[] sct = new SelectedChannelType[cov
                .getNumSampleDimensions()];
        ContrastEnhancement ce = sf.contrastEnhancement(this.ff.literal(1.0),
                ContrastMethod.NORMALIZE);
        for (int i = 0; i < 3; i++) {
            sct[i] = sf.createSelectedChannelType(
                    String.valueOf(channelNum[i]), ce);
        }

        RasterSymbolizer sym = sf.getDefaultRasterSymbolizer();
        ChannelSelection sel = sf.channelSelection(sct[RED], sct[GREEN],
                sct[BLUE]);
        sym.setChannelSelection(sel);

        return SLD.wrapSymbolizers(sym);
    }

    public File getBorderShape() {
        return borderShape;
    }

    public void setBorderShape(File borderShape) {
        this.borderShape = borderShape;
    }

    public File getBlueMarble() {
        return blueMarble;
    }

    public void setBlueMarble(File blueMarble) {
        this.blueMarble = blueMarble;
    }

    public GridCoverage2DReader getGridCoverageReader() {
        return gridCoverage2DReader;
    }

    public void setGridCoverageReader(GridCoverage2DReader gridCoverage2DReader) {
        this.gridCoverage2DReader = gridCoverage2DReader;
    }

    public static void main(String[] args) {
        JPanel imagePanel = new MapImagePanel();
        JFrame jFrame = new JFrame();
        jFrame.setPreferredSize(new Dimension(800, 600));
        jFrame.setLayout(new BorderLayout());
        jFrame.add(imagePanel, BorderLayout.CENTER);
        jFrame.setVisible(true);
    }
}
