package com.suntoon.map;

import com.suntoon.map.action.*;
import org.geotools.coverage.GridSampleDimension;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.data.CachingFeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.Hints;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.map.FeatureLayer;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.*;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;
import org.geotools.swing.control.JMapStatusBar;
import org.geotools.swing.tool.ScrollWheelTool;
import org.opengis.filter.FilterFactory2;
import org.opengis.style.ContrastMethod;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

/**
 * @ProjectionName desktop
 * @ClassName JMapPane
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/7 0007下午 2:39
 * @Version 1.0
 */
public class JMapPanel extends JMapPane{

    private static final long serialVersionUID = 682799622168380767L;

    //生成随机颜色
    private static Random rd = new Random();

    //地图工具条
    private JToolBar toolBar;

    private JMapStatusBar statusBar;

    //地图面板
    private JMapPane mapPane;

    private MapContent mapContent;

    private StyleFactory sf = CommonFactoryFinder.getStyleFactory();
    private FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
    private GridCoverage2DReader reader;

    public JMapPanel() {
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        initCompenent();
    }

    private void initCompenent() {
        Set<JMapFrame.Tool> toolSet = EnumSet.allOf(JMapFrame.Tool.class);
        JButton btn;
        ButtonGroup cursorToolGrp = new ButtonGroup();
        mapPane = new JMapPane();
        toolBar = new JToolBar();
        mapContent = new MapContent();

        if (toolSet.contains(JMapFrame.Tool.SCROLLWHEEL)) {
            mapPane.addMouseListener(new ScrollWheelTool(mapPane));
        }

        if (toolSet.contains(JMapFrame.Tool.POINTER)) {
            //btn = new JButton(new NoToolAction(mapPane));
            btn = new JButton(new NoToolAction(mapPane));
            //btn.setName("ToolbarPointerButton");
            toolBar.add(btn);
            cursorToolGrp.add(btn);
        }

        if (toolSet.contains(JMapFrame.Tool.ZOOM)) {
            btn = new JButton(new ZoomInAction(mapPane));
            //btn.setName("ToolbarZoomInButton");
            toolBar.add(btn);
            cursorToolGrp.add(btn);

            btn = new JButton(new ZoomOutAction(mapPane));
            //btn.setName("ToolbarZoomOutButton");
            toolBar.add(btn);
            cursorToolGrp.add(btn);

            toolBar.addSeparator();
        }

        if (toolSet.contains(JMapFrame.Tool.PAN)) {
            btn = new JButton(new PanAction(mapPane));
            //btn.setName("ToolbarPanButton");
            toolBar.add(btn);
            cursorToolGrp.add(btn);

            //toolBar.addSeparator();
        }

        if (toolSet.contains(org.geotools.swing.JMapFrame.Tool.INFO)) {
            btn = new JButton(new InfoAction(mapPane));
            //btn.setName("ToolbarInfoButton");
            toolBar.add(btn);

            toolBar.addSeparator();
        }

        if (toolSet.contains(org.geotools.swing.JMapFrame.Tool.RESET)) {
            btn = new JButton(new ResetAction(mapPane));
            //btn.setName("ToolbarResetButton");
            toolBar.add(btn);
        }

        mapPane.setMapContent(mapContent);

        statusBar = JMapStatusBar.createDefaultStatusBar(mapPane);
        this.add(toolBar, BorderLayout.NORTH);
        this.add(mapPane, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.SOUTH);
    }

    public Layer displayShpLayers(File shpFile) throws IOException {
        FileDataStore dataStore = FileDataStoreFinder.getDataStore(shpFile);
        ((ShapefileDataStore) dataStore).setCharset(Charset.forName("GBK"));
        SimpleFeatureSource shapefileSource = dataStore.getFeatureSource();
        CachingFeatureSource cache = new CachingFeatureSource(shapefileSource);

        // Create a basic style with yellow lines and no fill
        //Style shpStyle = SLD.createPolygonStyle(Color.BLUE, null, 0.0f);
        Style shpStyle = SLD.createSimpleStyle(shapefileSource.getSchema(), new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
        //Layer shpLayer = new FeatureLayer(shapefileSource, shpStyle);
        Layer shpLayer = new FeatureLayer(cache, shpStyle, shpFile.getName().substring(0, shpFile.getName().lastIndexOf(".")));
        System.out.println(shpLayer.getUserData());
        return shpLayer;
    }

    public Layer displayRasterLayers(File rasterFile) {
        AbstractGridFormat format = GridFormatFinder.findFormat( rasterFile );
        Hints hints = new Hints();
        if (format instanceof GeoTiffFormat) {
            hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
        }
        reader = format.getReader(rasterFile, hints);

        Style rasterStyle = createRGBStyle();

        Layer rasterLayer = new GridReaderLayer(reader, rasterStyle);
        //rasterLayer.setTitle("raster");
        return rasterLayer;
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

    public JMapStatusBar getStatusBar() {
        return statusBar;
    }

    public void setStatusBar(JMapStatusBar statusBar) {
        this.statusBar = statusBar;
    }

    public MapContent getMapContent() {
        return mapContent;
    }

    public void setMapContent(MapContent mapContent) {
        this.mapContent = mapContent;
    }

}
