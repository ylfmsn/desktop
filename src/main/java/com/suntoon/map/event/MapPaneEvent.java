package com.suntoon.map.event;

import com.suntoon.map.MapPane;

import java.util.EventObject;

/**
 * @ProjectionName desktop
 * @ClassName MapPaneEvent
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 10:47
 * @Version 1.0
 */
public class MapPaneEvent extends EventObject {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Type of MapPane event
     */
    public static enum Type {
        /**
         * The map pane has set a new context.
         */
        NEW_CONTEXT,

        /**
         * The map pane has set a new renderer.
         */
        NEW_RENDERER,

        /**
         * The map pane has been resized.
         */
        PANE_RESIZED,

        /**
         * The display area has been changed. This can include both changes in
         * bounds and in the coordinate reference system.
         */
        DISPLAY_AREA_CHANGED,

        /**
         * The map pane has started rendering features.
         */
        RENDERING_STARTED,

        /**
         * The map pane has stopped rendering features.
         */
        RENDERING_STOPPED,

        /**
         * The map pane is rendering features. The event will carry data that
         * can be retrieved as a floating point value between 0 and 1.
         */
        RENDERING_PROGRESS;
    }

    /** Type of mappane event */
    private Type type;

    /** Data associated with some event types */
    private Object data;

    /**
     * Constructor for an event with no associated data
     *
     * @param source
     *            the map pane issuing this event
     * @param type
     *            the type of event
     */
    public MapPaneEvent(MapPane source, Type type) {
        super(source);
        this.type = type;
    }

    /**
     * Constructor for an event with associated data. The new event object takes
     * ownership of the data object.
     *
     * @param source
     *            the map pane issuing this event
     * @param type
     *            the type of event
     * @param data
     *            the event data
     */
    public MapPaneEvent(MapPane source, Type type, Object data) {
        super(source);
        this.type = type;
        this.data = data;
    }

    /**
     * Gets the map pane which published this event.
     *
     * @return the source map pane
     */
    @Override
    public MapPane getSource() {
        return (MapPane) super.getSource();
    }

    /**
     * Get the type of this event
     *
     * @return event type
     */
    public Type getType() {
        return type;
    }

    /**
     * Get the data associated with this event, if any
     *
     * @return event data or {@code null} if not applicable
     */
    public Object getData() {
        return data;
    }
}
