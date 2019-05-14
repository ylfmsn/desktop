package com.suntoon.map.action;

import org.geotools.swing.MapPane;

/**
 * @ProjectionName desktop
 * @ClassName ZoomOutAction
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/14 0014下午 6:19
 * @Version 1.0
 */
public class ZoomOutAction extends org.geotools.swing.action.ZoomOutAction {

    private static final long serivalVersionUID = -1310517891302678341L;

    public ZoomOutAction(MapPane mapPane) {
        super(mapPane);
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "缩小");
    }
}
