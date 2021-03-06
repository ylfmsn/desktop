package com.suntoon.map.action;

import org.geotools.swing.MapPane;

/**
 * @ProjectionName desktop
 * @ClassName ZoomInAction
 * @Description 放大操作
 * @Author YueLifeng
 * @Date 2019/5/14 0014下午 6:17
 * @Version 1.0
 */
public class ZoomInAction extends org.geotools.swing.action.ZoomInAction {

    private static final long serivalVersionUID = -1310517891302678341L;

    public ZoomInAction(MapPane mapPane) {
        super(mapPane);
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "放大");
    }
}
