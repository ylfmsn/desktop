package com.suntoon.map.action;

import org.geotools.swing.MapPane;
import org.geotools.swing.action.ResetAction;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName ResetAction
 * @Description 地图初始化的动作
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 11:32
 * @Version 1.0
 */
public class ResetAction1 extends ResetAction {

    private static final long serialVersionUID = -1310517891302678241L;


    public ResetAction1(MapPane mapPane) {
        super(mapPane);
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/map/mActionZoomFullExtent.png")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "重置");
    }


}
