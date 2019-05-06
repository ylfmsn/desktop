package com.suntoon.map.control;

import com.suntoon.map.MapListener;
import com.suntoon.map.MapPane;
import com.suntoon.map.action.*;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JMapToolBar
 * @Description 地图工具条
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 10:23
 * @Version 1.0
 */
public class JMapToolBar1 extends JToolBar implements MapListener {

    private static final long serialVersionUID = 366621552595015884L;

    //地图工具类 构造器
    public JMapToolBar1(MapPane mapPane) {
        super();
        this.setMapPane(mapPane);
        this.initComponents();
    }

    //地图画布对象
    private MapPane canvas;

    @Override
    public MapPane getMapPane() {
        return this.canvas;
    }

    @Override
    public void setMapPane(MapPane mapPane) {
        this.canvas = mapPane;
    }

    //拖动操作
    private JButton btnPan;

    //恢复地图初始状态
    private JButton btnReset;

    //放大操作
    private JButton btnZoomIn;

    //缩小操作
    private JButton btnZoomOut;

    //shape文件操作对象
    private JButton btnShp;

    private JButton btnRaster;

    //测距工具
    private JButton btnDistance;

    //初始化控件
    private void initComponents() {

        // reset按钮
        btnReset = new JButton(new ResetAction(this.canvas));
        add(btnReset);

        //放大按钮
        btnZoomIn = new JButton(new ZoomInAction(this.canvas));
        add(btnZoomIn);

        //缩小按钮
        btnZoomOut = new JButton(new ZoomOutAction(this.canvas));
        add(btnZoomOut);

        //拖动按钮
        btnPan = new JButton(new PanAction(this.canvas));
        add(btnPan);

        //分割线
        this.addSeparator();

        //测距工具
        btnDistance = new JButton(new DistanceAction(canvas));
        add(btnDistance);

        //分割线
        this.addSeparator();

        //以下是和数据操作相关的功能点
        btnShp = new JButton(new OpenShpLayerAction(this.canvas));
        this.add(btnShp);

        btnRaster = new JButton(new OpenRasterLayerAction(this.canvas));
        this.add(btnRaster);
    }
}
