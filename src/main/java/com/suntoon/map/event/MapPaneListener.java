package com.suntoon.map.event;

/**
 * @ProjectionName desktop
 * @ClassName MapPaneListener
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 10:47
 * @Version 1.0
 */
public interface MapPaneListener {

    /**
     * Called by the map pane when a new map context has been set
     *
     * @param ev the event
     */
    public void onNewContext(MapPaneEvent ev);

    /**
     * Called by the map pane when a new renderer has been set
     *
     * @param ev the event
     */
    public void onNewRenderer(MapPaneEvent ev);

    /**
     * Called by the map pane when it has been resized
     *
     * @param ev the event
     */
    public void onResized(MapPaneEvent ev);

    /**
     * Called by the map pane when its display area has been
     * changed e.g. by zooming or panning
     *
     * @param ev the event
     */
    public void onDisplayAreaChanged(MapPaneEvent ev);

    /**
     * Called by the map pane when it has started rendering features
     *
     * @param ev the event
     */
    public void onRenderingStarted(MapPaneEvent ev);

    /**
     * Called by the map pane when it has stopped rendering features
     *
     * @param ev the event
     */
    public void onRenderingStopped(MapPaneEvent ev);

    /**
     * Called by the map pane when it is rendering features. The
     * event will be carrying data: a floating point value between
     * 0 and 1 indicating rendering progress.
     *
     * @param ev the event
     */
    public void onRenderingProgress(MapPaneEvent ev);
}
