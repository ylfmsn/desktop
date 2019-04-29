package com.suntoon.map.event;

import java.util.EventListener;

/**
 * @ProjectionName desktop
 * @InterfaceName MapMouseListener
 * @Description TODO
 * @Author ylf
 * @Date 2019/4/29 0029上午 10:33
 * @Version 1.0
 */
public interface MapMouseListener extends EventListener {
    /**
            * Respond to a mouse click event received from the map pane
     *
             * @param ev the mouse event
     */
    public void onMouseClicked(MapMouseEvent ev);

    /**
     * Respond to a mouse dragged event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseDragged(MapMouseEvent ev);

    /**
     * Respond to a mouse entered event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseEntered(MapMouseEvent ev);

    /**
     * Respond to a mouse exited event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseExited(MapMouseEvent ev);

    /**
     * Respond to a mouse movement event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseMoved(MapMouseEvent ev);

    /**
     * Respond to a mouse button press event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMousePressed(MapMouseEvent ev);

    /**
     * Respond to a mouse button release event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseReleased(MapMouseEvent ev);

    /**
     * Respond to a mouse wheel scroll event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseWheelMoved(MapMouseEvent ev);
}
