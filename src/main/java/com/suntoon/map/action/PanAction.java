package com.suntoon.map.action;

import org.geotools.swing.MapPane;

/**
 * @ProjectionName desktop
 * @ClassName PanAction
 * @Description 地图拖动
 * @Author YueLifeng
 * @Date 2019/5/14 0014下午 6:15
 * @Version 1.0
 */
public class PanAction extends org.geotools.swing.action.PanAction {

    private static final long serivalVersionUID = -1310517891302678341L;

    public PanAction(MapPane mapPane) {
        super(mapPane);
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "拖动");
    }
}
