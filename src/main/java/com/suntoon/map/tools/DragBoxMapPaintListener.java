package com.suntoon.map.tools;

import com.suntoon.map.event.MapPaintListener;

import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName DragBoxMapPaintListener
 * @Description 实现拖动框的效果
 * @Author YueLifeng
 * @Date 2019/4/29 0029下午 2:33
 * @Version 1.0
 */
public interface DragBoxMapPaintListener extends MapPaintListener {

    //当前是否是拖动状态
    public boolean isDrag();

    //屏幕操作起始位置
    public Point getStartDevicePos();

    //屏幕操作结束位置
    public Point getEndDevicePos();

    @Override
    public default void afterPaint(Graphics2D g2d, int dx, int dy) {
        if (g2d != null && this.isDrag()) {
            Color color = g2d.getColor();
            g2d.setColor(Color.GREEN);
            int x = Math.min(getStartDevicePos().x, getEndDevicePos().x);
            int y = Math.min(getStartDevicePos().y, getEndDevicePos().y);
            int w = Math.abs(getEndDevicePos().x - getStartDevicePos().x);
            int h = Math.abs(getEndDevicePos().y - getStartDevicePos().y);
            g2d.drawRect(x, y, w, h);
            g2d.setColor(color);
        }
    }
}
