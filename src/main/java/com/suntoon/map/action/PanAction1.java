package com.suntoon.map.action;

import org.geotools.swing.MapPane;
import org.geotools.swing.action.PanAction;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName PanAction
 * @Description 移动操作
 * @Author YueLifeng
 * @Date 2019/4/29 0029下午 2:51
 * @Version 1.0
 */
public class PanAction1 extends PanAction {

    private static final long serialVersionUID = -8580473973445330548L;

    //移动操作
    public PanAction1(MapPane mapPane) {
        super(mapPane);
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/map/pan_mode.gif")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "拖动");
    }
}
