package com.suntoon.map.tools;

import com.suntoon.map.event.MapMouseEvent;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @ProjectionName desktop
 * @ClassName PanTool
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/29 0029下午 2:55
 * @Version 1.0
 */
public class PanTool extends AbstractZoomTool {

    //工具名称
    public static final String TOOL_NAME = "PanTool";

    //工具提示文本
    public static final String TOOL_TIP = "PanTool";

    private Cursor cursor;

    //鼠标按下的时候的拖动点
    private final Point beginPos;

    //鼠标松手的位置点
    private final Point endPos;

    //是否拖动状态
    private volatile boolean panning = false;

    //构造器
    public PanTool() {
        panning = false;
        beginPos = new Point(0, 0);
        endPos = new Point(0, 0);
    }

    /**
     * Respond to a mouse button press event from the map mapPane. This may
     * signal the start of a mouse drag. Records the event's window position.
     *
     * @param ev
     *            the mouse event
     */
    @Override
    public void onMousePressed(MapMouseEvent ev) {
        super.onMousePressed(ev);
        if (ev.getButton() == MouseEvent.BUTTON1) {
            beginPos.setLocation(ev.getPoint());
            endPos.setLocation(ev.getPoint());
            panning = true;
        }
    }

    /**
     * Respond to a mouse dragged event. Calls
     * {@link //com.adcc.geotools.swing.MapPane#moveImage()}
     *
     * @param ev
     *            the mouse event
     */
    @Override
    public void onMouseDragged(MapMouseEvent ev) {
        super.onMouseDragged(ev);
        if (panning) {
            endPos.setLocation(ev.getPoint());
            if (!beginPos.equals(endPos)) {
                getMapPane().moveImage(endPos.x - beginPos.x, endPos.y - beginPos.y);
            }
        }
    }

    /**
     * If this button release is the end of a mouse dragged event, requests the
     * map mapPane to repaint the display
     *
     * @param ev
     *            the mouse event
     */
    @Override
    public void onMouseReleased(MapMouseEvent ev) {
        super.onMouseReleased(ev);
        if (panning && !beginPos.equals(endPos)) {
            getMapPane().move(endPos.x - beginPos.x, endPos.y - beginPos.y);
        }
        panning = false;
        beginPos.setLocation(0, 0);
        endPos.setLocation(0, 0);
    }

    /**
     * Get the mouse cursor for this tool
     */
    @Override
    public Cursor getCursor() {
        return cursor;
    }
}
