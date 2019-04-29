package com.suntoon.map.tools;

import com.suntoon.map.MapListener;
import com.suntoon.map.MapPane;
import com.suntoon.map.event.MapMouseAdapter;

import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName CursorTool
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 11:08
 * @Version 1.0
 */
public abstract class CursorTool extends MapMouseAdapter implements MapListener {

    private MapPane mapPane;

    /**
     * Get the map pane that this tool is servicing
     *
     * @return the map pane
     */
    @Override
    public MapPane getMapPane() {
        return mapPane;
    }

    /**
     * Set the map pane that this cursor tool is associated with
     * @param pane the map pane
     * @throws IllegalArgumentException if mapPane is null
     */
    @Override
    public void setMapPane(MapPane pane) {
        if (pane == null) {
            throw new IllegalArgumentException("pane arg must not be null");
        }

        this.mapPane = pane;
        this.mapPane.addMouseListener(this);
    }

    /**
     *
     *  when change to another CursorTool's call
     */
    public void unUsed(){
        mapPane.removeMouseListener(this);
    }

    /**
     * Get the cursor for this tool. Sub-classes should override this
     * method to provide a custom cursor.
     *
     * @return the default cursor
     */
    public Cursor getCursor() {
        return Cursor.getDefaultCursor();
    }

}
