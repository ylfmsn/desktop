package com.suntoon.map;

/**
 * @ProjectionName desktop
 * @ClassName MapListener
 * @Description 设置画布的操作，符合接口隔离原则
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 10:23
 * @Version 1.0
 */
public interface MapListener {

    //获取当前的画布
    public MapPane getMapPane();

    //设置当前的画布
    public void setMapPane(MapPane mapPane);
}
