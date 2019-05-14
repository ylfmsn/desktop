package com.suntoon.map.action;

import org.geotools.swing.MapPane;

/**
 * @ProjectionName desktop
 * @ClassName NoToolAction
 * @Description 取消工具功能
 * @Author YueLifeng
 * @Date 2019/5/14 0014下午 6:20
 * @Version 1.0
 */
public class NoToolAction extends org.geotools.swing.action.NoToolAction {

    private static final long serivalVersionUID = -1310517891302678341L;

    public NoToolAction(MapPane mapPane) {
        super(mapPane);
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "取消工具功能");
    }
}
