package com.suntoon.test;

import com.suntoon.map.JMapCanvas;
import com.suntoon.map.JMapFrame;
import com.suntoon.map.MyMapFrame;
import org.geotools.coverage.GridSampleDimension;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Parameter;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.Hints;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.map.FeatureLayer;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.*;
import org.geotools.swing.JMapPane;
import org.geotools.swing.action.*;
import org.geotools.swing.control.JMapStatusBar;
import org.geotools.swing.data.JParameterListWizard;
import org.geotools.swing.tool.ScrollWheelTool;
import org.geotools.swing.wizard.JWizard;
import org.geotools.util.KVP;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.opengis.filter.FilterFactory2;
import org.opengis.style.ContrastMethod;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName TestMap
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/6 0006下午 12:58
 * @Version 1.0
 */
public class TestMap extends JMapFrame {

    private static final long serialVersionUID = 682799622168380767L;

    //生成随机颜色的种子
    private static Random rd = new Random();

    //地图工具条
    //private JMapToolBar toolBar;
    private JToolBar toolBar;

    //地图对象
    private JMapCanvas canvas;

    private StyleFactory sf = CommonFactoryFinder.getStyleFactory();
    private FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

    private MyMapFrame frame;
    private GridCoverage2DReader reader;

    final MapContent map = new MapContent();

    /*public static void main(String[] args) throws Exception {

        try{
            //设置本属性将改变窗口边框样式定义
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageLab me = new ImageLab();
        me.getLayersAndDisplay();
    }*/
    // docs end main

    // docs start get layers
    /**
     * Prompts the user for a GeoTIFF file and a Shapefile and passes them to the displayLayers
     * method
     */
    private void getLayersAndDisplay() throws Exception {
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
            System.exit(0);
        }
        File imageFile = (File) wizard.getConnectionParameters().get("image");
        File shapeFile = (File) wizard.getConnectionParameters().get("shape");
        displayLayers(imageFile, shapeFile);
    }

    // docs end get layers

    // docs start display layers
    /**
     * Displays a GeoTIFF file overlaid with a Shapefile
     *
     * @param rasterFile
     *            the GeoTIFF file
     * @param shpFile
     *            the Shapefile
     */
    private MapContent displayLayers(File rasterFile, File shpFile) throws Exception {
        AbstractGridFormat format = GridFormatFinder.findFormat( rasterFile );
        //this is a bit hacky but does make more geotiffs work
        Hints hints = new Hints();
        if (format instanceof GeoTiffFormat) {
            hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
        }
        reader = format.getReader(rasterFile, hints);


        // Initially display the raster in greyscale using the
        // data from the first image band
        Style rasterStyle = createRGBStyle();

        // Connect to the shapefile
        FileDataStore dataStore = FileDataStoreFinder.getDataStore(shpFile);
        SimpleFeatureSource shapefileSource = dataStore
                .getFeatureSource();

        // Create a basic style with yellow lines and no fill
        //Style shpStyle = SLD.createPolygonStyle(Color.BLUE, null, 0.0f);
        Style shpStyle = SLD.createSimpleStyle(shapefileSource.getSchema(),
                new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));

        // Set up a MapContent with the two layers

        map.setTitle("ImageLab");

        Layer rasterLayer = new GridReaderLayer(reader, rasterStyle);
        map.addLayer(rasterLayer);

        Layer shpLayer = new FeatureLayer(shapefileSource, shpStyle);
        map.addLayer(shpLayer);

        return map;

        // Create a JMapFrame with a menu to choose the display style for the
        //frame = new MyMapFrame(map);
        //frame.setSize(800, 600);
        //frame.enableStatusBar(true);
        //frame.enableTool(JMapFrame.Tool.ZOOM, JMapFrame.Tool.PAN, JMapFrame.Tool.RESET);
        //frame.enableToolBar(true);
        //frame.enableLayerTable(true);

        // Finally display the map frame.
        // When it is closed the app will exit.
        //frame.setVisible(true);
    }

    // docs end display layers


    // docs start create greyscale style
    /**
     * Create a Style to display a selected band of the GeoTIFF image
     * as a greyscale layer
     *
     * @return a new Style instance to render the image in greyscale
     */
    private Style createGreyscaleStyle() {
        GridCoverage2D cov = null;
        try {
            cov = reader.read(null);
        } catch (IOException giveUp) {
            throw new RuntimeException(giveUp);
        }
        int numBands = cov.getNumSampleDimensions();
        Integer[] bandNumbers = new Integer[numBands];
        for (int i = 0; i < numBands; i++) { bandNumbers[i] = i+1; }
        Object selection = JOptionPane.showInputDialog(
                frame,
                "Band to use for greyscale display",
                "Select an image band",
                JOptionPane.QUESTION_MESSAGE,
                null,
                bandNumbers,
                1);
        if (selection != null) {
            int band = ((Number)selection).intValue();
            return createGreyscaleStyle(band);
        }
        return null;
    }


    /**
     * Create a Style to display the specified band of the GeoTIFF image
     * as a greyscale layer.
     * <p>
     * This method is a helper for createGreyScale() and is also called directly
     * by the displayLayers() method when the application first starts.
     *
     * @param band the image band to use for the greyscale display
     *
     * @return a new Style instance to render the image in greyscale
     */
    private Style createGreyscaleStyle(int band) {
        ContrastEnhancement ce = sf.contrastEnhancement(ff.literal(1.0), ContrastMethod.NORMALIZE);
        SelectedChannelType sct = sf.createSelectedChannelType(String.valueOf(band), ce);

        RasterSymbolizer sym = sf.getDefaultRasterSymbolizer();
        ChannelSelection sel = sf.channelSelection(sct);
        sym.setChannelSelection(sel);

        return SLD.wrapSymbolizers(sym);
    }

    // docs end create greyscale style

    // docs start create rgb style
    /**
     * This method examines the names of the sample dimensions in the provided coverage looking for
     * "red...", "green..." and "blue..." (case insensitive match). If these names are not found
     * it uses bands 1, 2, and 3 for the red, green and blue channels. It then sets up a raster
     * symbolizer and returns this wrapped in a Style.
     *
     * @return a new Style object containing a raster symbolizer set up for RGB image
     */
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

    public TestMap() {
        super();
        //this.setSize(1224, 840);
        this.setBounds(100, 100, 1224, 840);
        this.setTitle("suntoon desktoop v1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 默认窗口最大化
        this.initCompents();

        this.setVisible(true);
    }

    private void initCompents() {

        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1024, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label = new JLabel("林调通", JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        this.add(panel,BorderLayout.NORTH);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setPreferredSize(new Dimension(200, 700));
        panel1.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label1 = new JLabel("本地数据", JLabel.CENTER);
        panel1.add(label1, BorderLayout.CENTER);
        this.add(panel1,BorderLayout.EAST);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(200, 700));
        panel2.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        JLabel label2 = new JLabel("平板数据", JLabel.CENTER);
        panel2.add(label2, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.WEST);

        //JPanel panel3 = new JPanel(new BorderLayout());
        //panel3.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        //panel3.add(toolBar, BorderLayout.NORTH);
        //panel3.add(canvas, BorderLayout.CENTER);

        File rasterFile = new File("E:\\ArcGISdata\\binlinqu\\L17-prj.tif");
        File shpFile = new File("E:\\ArcGISdata\\bou4_4m\\BOUNT_line.shp");
        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        Set<org.geotools.swing.JMapFrame.Tool> toolSet = EnumSet.allOf(org.geotools.swing.JMapFrame.Tool.class);
        JButton btn;
        JMapPane mapPane = new JMapPane();
        toolBar = new JToolBar();
        ButtonGroup cursorToolGrp = new ButtonGroup();
        if (toolSet.contains(org.geotools.swing.JMapFrame.Tool.SCROLLWHEEL)) {
            mapPane.addMouseListener(new ScrollWheelTool(mapPane));
        }
        if (toolSet.contains(org.geotools.swing.JMapFrame.Tool.POINTER)) {
            btn = new JButton(new NoToolAction(mapPane));
            btn.setName("ToolbarPointerButton");
            toolBar.add(btn);
            cursorToolGrp.add(btn);
        }

        if (toolSet.contains(org.geotools.swing.JMapFrame.Tool.ZOOM)) {
            btn = new JButton(new ZoomInAction(mapPane));
            btn.setName("ToolbarZoomInButton");
            toolBar.add(btn);
            cursorToolGrp.add(btn);

            btn = new JButton(new ZoomOutAction(mapPane));
            btn.setName("ToolbarZoomOutButton");
            toolBar.add(btn);
            cursorToolGrp.add(btn);

            toolBar.addSeparator();
        }

        if (toolSet.contains(org.geotools.swing.JMapFrame.Tool.PAN)) {
            btn = new JButton(new PanAction(mapPane));
            btn.setName("ToolbarPanButton");
            toolBar.add(btn);
            cursorToolGrp.add(btn);

            toolBar.addSeparator();
        }

        if (toolSet.contains(org.geotools.swing.JMapFrame.Tool.INFO)) {
            btn = new JButton(new InfoAction(mapPane));
            btn.setName("ToolbarInfoButton");
            toolBar.add(btn);

            toolBar.addSeparator();
        }

        if (toolSet.contains(org.geotools.swing.JMapFrame.Tool.RESET)) {
            btn = new JButton(new ResetAction(mapPane));
            btn.setName("ToolbarResetButton");
            toolBar.add(btn);
        }

        //panel.add(toolBar, "grow");
        MapContent mapContent = null;
        JMapStatusBar statusBar = null;
        try {
            mapContent = displayLayers(rasterFile, shpFile);
            mapPane.setMapContent(mapContent);
            //toolBar = new JMapToolBar(canvas);
            statusBar = JMapStatusBar.createDefaultStatusBar(mapPane);
            panel3.add(toolBar, BorderLayout.NORTH);
            panel3.add(mapPane, BorderLayout.CENTER);
            //panel3.add(statusBar, BorderLayout.SOUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.add(panel3, BorderLayout.CENTER);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.setPreferredSize(new Dimension(200, 40));
        panel4.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        //JLabel label4 = new JLabel("map", JLabel.CENTER);
        panel4.add(statusBar, BorderLayout.CENTER);
        this.add(panel4,BorderLayout.SOUTH);
    }


    public static void main(String args[]) {

        try{
            //设置本属性将改变窗口边框样式定义
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestMap test = new TestMap();
    }
























}
