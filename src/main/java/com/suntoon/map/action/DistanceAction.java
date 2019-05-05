package com.suntoon.map.action;

import com.suntoon.map.MapPane;
import com.suntoon.map.tools.DistanceTool;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName DistanceAction
 * @Description 测距操作
 * @Author YueLifeng
 * @Date 2019/4/29 0029下午 3:23
 * @Version 1.0
 */
public class DistanceAction extends AbstractMapAction {

    private static final long serialVersionUID = 2667458318140535983L;

    //测距操作
    public DistanceAction(MapPane mapPane) {
        super(mapPane);
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/map/distance.png")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "测距");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getMapPane() != null)
            this.getMapPane().setCursorTool(new DistanceTool());
    }
}
