package com.suntoon.map.action;

import com.suntoon.map.MapPane;
import com.suntoon.map.tools.ZoomInTool;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName ZoomInAction
 * @Description 地图放大操作
 * @Author YueLifeng
 * @Date 2019/4/29 0029下午 2:12
 * @Version 1.0
 */
public class ZoomInAction extends AbstractMapAction  {

    private static final long serialVersionUID = -5480011168098849229L;

    //放大操作
    public ZoomInAction(MapPane mapPane) {
        super(mapPane);
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/map/mActionZoomIn.png")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "放大");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getMapPane() != null)
            this.getMapPane().setCursorTool(new ZoomInTool());
    }
}
