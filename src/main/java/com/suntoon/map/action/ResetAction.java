package com.suntoon.map.action;

import com.suntoon.map.MapPane;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName ResetAction
 * @Description 地图初始化的动作
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 11:32
 * @Version 1.0
 */
public class ResetAction extends AbstractMapAction {

    private static final long serialVersionUID = -1310517891302678241L;

    public ResetAction(MapPane mapPane) {
        super(mapPane);
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("map/mActionZoomFullExtent.png")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "重置");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getMapPane() != null)
            this.getMapPane().reset();
    }
}
