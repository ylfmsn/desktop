package com.suntoon.map.action;

import org.geotools.swing.MapPane;

/**
 * @ProjectionName desktop
 * @ClassName ResetAction
 * @Description 地图重置
 * @Author YueLifeng
 * @Date 2019/5/14 0014下午 5:52
 * @Version 1.0
 */
public class ResetAction extends org.geotools.swing.action.ResetAction {

    private static final long serivalVersionUID = -1310517891302678241L;

    public ResetAction(MapPane mapPane) {
        super(mapPane);
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "重置");
    }
}
