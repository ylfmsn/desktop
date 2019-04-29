package com.suntoon.map.event;

import java.awt.event.*;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName MapMouseEventDispatcher
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 10:31
 * @Version 1.0
 */
public interface MapMouseEventDispatcher extends MouseListener, MouseMotionListener, MouseWheelListener {

    /**
     * Adds a listener for map pane mouse events.
     *
     * @param listener the new listener
     * @return true if successful; false otherwise
     * @throws IllegalArgumentException if the {@code listener} is {@code null}
     */
    public boolean addMouseListener(MapMouseListener listener);

    /**
     * Removes the given listener.
     *
     * @param listener the listener to remove
     * @return true if successful; false otherwise
     * @throws IllegalArgumentException if the {@code listener} is {@code null}
     */
    public boolean removeMouseListener(MapMouseListener listener);

    /**
     * Removes all listeners.
     */
    public void removeAllListeners();

    /**
     * 获取全部的事件资源
     * @return
     */
    public List<MapMouseListener> getAllListeners();

    /**
     * Converts an incoming Java AWT mouse event to a {@linkplain MapMouseEvent}.
     *
     */
    public MapMouseEvent convertEvent(MouseEvent ev);

    /**
     * Converts an incoming Java AWT mouse wheel event to a {@linkplain MapMouseEvent}.
     *
     */
    public MapMouseEvent convertEvent(MouseWheelEvent ev);

}
