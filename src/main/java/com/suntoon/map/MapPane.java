package com.suntoon.map;

import com.suntoon.map.event.MapMouseEventDispatcher;
import com.suntoon.map.event.MapMouseListener;
import com.suntoon.map.event.MapPaintListener;
import com.suntoon.map.event.MapPaneListener;
import com.suntoon.map.plugins.Plugin;
import com.suntoon.map.tools.CursorTool;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.MapContent;
import org.geotools.renderer.GTRenderer;
import org.opengis.geometry.Envelope;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * @ProjectionName desktop
 * @InterfaceName MapPane
 * @Description 地图画布对外公布的操作接口
 * @Author ylf
 * @Date 2019/4/29 0029上午 10:25
 * @Version 1.0
 */
public interface MapPane {

    //获取当前的地图上下文对象
    public MapContent getMapContent();

    //设置当前的地图上下文
    public void setMapContent(MapContent content);

    //获取画布的背景填充色
    public Color getBackground();

    //设置画布的背景填充色
    public void setBackground(Color color);

    //获取鼠标操作包装对象
    public MapMouseEventDispatcher getMouseEventDispatcher();

    //设置鼠标操作包装对象
    public void setMouseEventDispatcher(MapMouseEventDispatcher dispatcher);

    //获取当前的显示地理位置
    public ReferencedEnvelope getDisplayArea();

    //设置当前的地理坐标系列
    public void setDisplayArea(Envelope envelope);

    //转换地图到全景区域并重绘制
    public void reset();

    //获取屏幕坐标系到地理坐标系的转换
    public AffineTransform getScreenToWorldTransform();

    //获取地理信息坐标系统到屏幕的转换
    public AffineTransform getWorldToScreenTransform();

    //添加地图操作监听
    public void addMapPaneListener(MapPaneListener listener);

    //移除地图操作事件监听
    public void removeMapPaneListener(MapPaneListener listener);

    //添加鼠标事件
    public void addMouseListener(MapMouseListener listener);

    //移除鼠标事件
    public void removeMouseListener(MapMouseListener listener);

    //增加绘制listener
    public void addPaintListener(MapPaintListener listener);

    //移除绘制listener
    public void removePaintListener(MapPaintListener listener);

    /**
     * @Author YueLifeng
     * @Description //加入插件 一个插件仅允许插入一次
     * @Date 上午 11:05 2019/4/29 0029
     * @param plugin
     * @return void
     */
    public void addPlugin(Plugin plugin);

    /**
     * @Author YueLifeng
     * @Description //移除插件
     * @Date 上午 11:06 2019/4/29 0029
     * @param plugin
     * @return void
     */
    public void removePlugin(Plugin plugin);

    /**
     * 获取当前的操作工具对象
     *
     * @return
     */
    public CursorTool getCursorTool();

    /**
     * 设置当前的操作工具对象
     *
     * @param tool
     */
    public void setCursorTool(CursorTool tool);

    /**
     * 拖动图片位置的操作
     *
     * @param dx
     *            x轴偏移量
     * @param dy
     *            y轴偏移量
     */
    public void moveImage(int dx, int dy);

    /**
     * 将视界平移并重绘
     * @param dx x轴平移量
     * @param dy y轴平移量
     */
    public void move(int dx, int dy);

    /**
     * 重绘地图操作
     *
     * @param resize
     *            当size发生变化对手执行的操作
     */
    public void repaint(boolean resize);

    /**
     * 刷新，不重绘地图
     */
    public void refresh();

    /**
     * 获取世界的中心点，获取的是地理信息位置 返回的是world coordinates
     *
     * @return
     */
    public DirectPosition2D getMapCenter();

    /**
     * 获取当前画布的可视范围大小(像素)
     * @return
     */
    public Rectangle getVisibleRectangle();

    /**
     * 获取绘制对象
     *
     * @return
     */
    public GTRenderer getRenderer();

    /**
     * 设置绘制对象
     *
     * @param renderer
     */
    public void setRenderer(GTRenderer renderer);
}
