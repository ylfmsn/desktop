package com.suntoon.map.plugins;

import com.suntoon.map.MapPane;

/**
 * @ProjectionName desktop
 * @ClassName PluginBase
 * @Description 所有插件的基类
 * @Author YueLifeng
 * @Date 2019/5/5 0005上午 10:54
 * @Version 1.0
 */
public abstract class PluginBase implements Plugin {

    //插件名称
    private String title;

    //所在分组
    private String group;

    //当前的画布对象
    private MapPane canvas;

    /**
     * @Author YueLifeng
     * @Description 构造器
     * @Date 上午 10:57 2019/5/5 0005
     * @param title
     *          插件名称
     * @param group
     *          插件分组
     * @param map
     *          画布
     * @return
     */
    public PluginBase(String title, String group, MapPane map) {
        this.title = title;
        this.group = group;
        this.canvas = map;
    }

    @Override
    public MapPane getMapPane() {
        return canvas;
    }

    @Override
    public void setMapPane(MapPane mapPane) {
        this.canvas = mapPane;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getGroup() {
        return this.group;
    }
}
