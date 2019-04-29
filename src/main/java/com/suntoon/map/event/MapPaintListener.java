package com.suntoon.map.event;

import java.awt.*;
import java.util.EventListener;

/**
 * @ProjectionName desktop
 * @ClassName MapPaintListener
 * @Description 绘制地图的linster
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 10:58
 * @Version 1.0
 */
public interface MapPaintListener extends EventListener {

    //绘制之前执行的操作 直接绘制到图片上的方法
    public default void beforePaint(Graphics2D g2d) {

    }

    //地图绘制完成后执行的操作 直接绘制到画布上的方法
    public void afterPaint(Graphics2D g2d, int dx, int dy);
}
