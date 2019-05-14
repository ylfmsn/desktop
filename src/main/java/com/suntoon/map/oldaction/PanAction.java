package com.suntoon.map.oldaction;

import com.suntoon.map.MapPane;
import com.suntoon.map.tools.PanTool;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName PanAction
 * @Description 移动操作
 * @Author YueLifeng
 * @Date 2019/4/29 0029下午 2:51
 * @Version 1.0
 */
public class PanAction extends AbstractMapAction {

    private static final long serialVersionUID = -8580473973445330548L;

    //移动操作
    public PanAction(MapPane mapPane) {
        super(mapPane);
        this.putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/map/pan_mode.gif")));
        this.putValue(NAME, "");
        this.putValue(SHORT_DESCRIPTION, "拖动");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getMapPane() != null)
            this.getMapPane().setCursorTool(new PanTool());
    }
}
