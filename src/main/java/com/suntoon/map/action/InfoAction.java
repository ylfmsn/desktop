package com.suntoon.map.action;

import org.geotools.swing.MapPane;

/**
 * @ProjectionName desktop
 * @ClassName InfoAction
 * @Description 点击图层查看属性信息的控件
 * @Author YueLifeng
 * @Date 2019/5/14 0014下午 6:10
 * @Version 1.0
 */
public class InfoAction extends org.geotools.swing.action.InfoAction {

    private static final long serivalVersionUID = -1310517891302678341L;

    public InfoAction(MapPane mapPane) {
        super(mapPane);
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "属性信息");
    }
}
