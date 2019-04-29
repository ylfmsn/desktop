package com.suntoon.map.action;

import com.suntoon.map.MapPane;
import com.suntoon.map.tools.ZoomOutTool;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName ZoomOutAction
 * @Description 缩小操作
 * @Author YueLifeng
 * @Date 2019/4/29 0029下午 2:42
 * @Version 1.0
 */
public class ZoomOutAction extends AbstractMapAction {

    private static final long serialVersionUID = 2667458318140535983L;

    //缩小操作 构造器
    public ZoomOutAction(MapPane mapPane) {
        super(mapPane);
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("map/mActionZoomOut.png")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "缩小");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getMapPane() != null)
            this.getMapPane().setCursorTool(new ZoomOutTool());
    }
}
