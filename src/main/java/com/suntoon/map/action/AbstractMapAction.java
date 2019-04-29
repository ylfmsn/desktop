package com.suntoon.map.action;

import com.suntoon.map.MapPane;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName AbstractMapAction
 * @Description 所有的工具控件的基类
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 11:32
 * @Version 1.0
 */
public abstract class AbstractMapAction extends AbstractAction implements MapAction {

    private static final long serialVersionUID = -3214956077693326058L;

    //画布对象
    protected MapPane mapPane;

    //所有的工具控件的基类
    public AbstractMapAction(MapPane mapPane) {
        this.setMapPane(mapPane);
    }

    //画布对象
    @Override
    public MapPane getMapPane() {
        return this.mapPane;
    }

    //设置画布对象
    @Override
    public void setMapPane(MapPane mapPane) {
        this.mapPane = mapPane;
    }
}
