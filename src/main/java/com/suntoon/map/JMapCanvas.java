package com.suntoon.map;

import com.suntoon.map.event.*;
import com.suntoon.map.plugins.Plugin;
import com.suntoon.map.tools.CursorTool;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.map.MapViewport;
import org.geotools.map.event.MapBoundsListener;
import org.geotools.map.event.MapLayerListListener;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.label.LabelCacheImpl;
import org.geotools.renderer.lite.LabelCache;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.util.logging.Logging;
import org.opengis.geometry.Envelope;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @ProjectionName desktop
 * @ClassName JMapCanvas
 * @Description javafx实现geotools画布对象
 * @Author YueLifeng
 * @Date 2019/4/30 0030上午 8:16
 * @Version 1.0
 */
public class JMapCanvas extends JPanel implements MapPane, MapLayerListListener, MapBoundsListener {

    private static final long serialVersionUID = 9110483825678702033L;

    //重绘间隔
    public static final int DEFAULT_RESIZING_PAINT_DELAY = 500;    //delay in milliseconds

    //当前的日志对象
    protected static final Logger LOGGER = Logging.getLogger("org.geotools.JavaMapPane");

    //当前的视界范围
    protected ReferencedEnvelope fullExtent;

    //当前地图的上下文
    protected MapContent mapContent;

    //绘制上下文对象
    protected GTRenderer renderer;

    //如果用户在屏幕上显示窗格之前设置显示区域，我们将使用此字段存储所请求的包罗范围，并在显示窗格时引用它
    protected ReferencedEnvelope pendingDisplayArea;

    //注册的地图事件
    protected List<MapPaneListener> paneListeners = new LinkedList<>();

    //绘制事件
    protected List<MapPaintListener> paintListeners = new LinkedList<>();

    //插件列表
    protected List<Plugin> plugins = new LinkedList<>();

    //缓存（应该是标签）
    protected LabelCache labelCache;

    //重绘的时候是否清理标签缓存数据
    private volatile boolean clearLabelCache = true;

    //当前的内存图片
    private BufferedImage baseImage;

    //内存2D画布对象
    private Graphics2D memory2D;

    //已经会治好的地图偏移量
    private volatile Point offsetImage = new Point(0, 0);

    //鼠标操作事件包装对象
    protected MapMouseEventDispatcher mapMouseEventDispatcher;

    //当前的鼠标操作工具
    private CursorTool cursorTool;

    //缓存起来的世界到屏幕转换的转换对象
    private AffineTransform worldToScreen;

    //缓存起来的屏幕到世界的转换对象
    private AffineTransform screenToWorld;

    //背景的填充色
    private Color backgroundColor = new Color(100, 106, 116);

    /**
     * @Author YueLifeng
     * @Description //javafx实现的geotools画布对象
     * @Date 上午 8:59 2019/4/30 0030
     * @param content
     * @return
     */
    public JMapCanvas(MapContent content) {
        //实现画布
        doSetRenderer(new StreamingRenderer());
        this.setMapContent(content);

        mapMouseEventDispatcher = new DefaultMapMouseEventDispatcher(this);
        this.addMouseListener(mapMouseEventDispatcher);
        this.addMouseMotionListener(mapMouseEventDispatcher);
        this.addMouseWheelListener(mapMouseEventDispatcher);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JMapCanvas.this.repaint(true);
            }
        });
    }

    // 当前地图的上下文
    public MapContent getMapContent() {
        return mapContent;
    }

    /**
     * @Author YueLifeng
     * @Description //重新设置地图上下文
     * @Date 上午 10:19 2019/4/30 0030
     * @param content
     * @return void
     */
    public void setMapContent(MapContent content) {

        if (this.mapContent != content) {

            if (this.mapContent != null) {
                this.mapContent.removeMapLayerListListener(this);
            }

            this.mapContent = content;

            if (content != null) {
                MapViewport viewport = mapContent.getViewport();
                viewport.setMatchingAspectRatio(true);
                Rectangle rect = this.getVisibleRectangle();
                if (!rect.isEmpty()) {
                    viewport.setScreenArea(rect);
                }
                this.mapContent.addMapLayerListListener(this);
                this.mapContent.addMapBoundsListener(this);

                //将所有图层设置为信息工具的默认选择
                for (Layer layer : content.layers()) {
                    layer.setSelected(true);
                }

                setFullExtent();
                doSetDisplayArea(mapContent.getViewport().getBounds());
            }

            if (renderer != null) {
                renderer.setMapContent(this.mapContent);
            }

            MapPaneEvent ev = new MapPaneEvent(this, MapPaneEvent.Type.NEW_CONTEXT);
            publishEvent(ev);
        }
    }

    @Override
    public GTRenderer getRenderer() {
        if (renderer == null) {
            doSetRenderer(new StreamingRenderer());
        }
        return renderer;
    }

    @Override
    public void setRenderer(GTRenderer renderer) {
        doSetRenderer(renderer);
    }

    @Override
    public Color getBackground() {
        return this.backgroundColor;
    }

    //设置画布的背景颜色
    @Override
    public void setBackground(Color bg) {
        if (bg != null) {
            this.backgroundColor = bg;
        }

        super.setBackground(bg);
    }

    @Override
    public MapMouseEventDispatcher getMouseEventDispatcher() {
        return mapMouseEventDispatcher;
    }

    @Override
    public void setMouseEventDispatcher(MapMouseEventDispatcher dispatcher) {
        if (mapMouseEventDispatcher != dispatcher) {
            if (this.mapMouseEventDispatcher != null && dispatcher != null) {
                List<MapMouseListener> listeners = mapMouseEventDispatcher.getAllListeners();
                for (MapMouseListener l : listeners) {
                    dispatcher.addMouseListener(l);
                }
                mapMouseEventDispatcher.removeAllListeners();
            }
            this.mapMouseEventDispatcher = dispatcher;
        }
    }

    @Override
    public ReferencedEnvelope getDisplayArea() {
        ReferencedEnvelope aoi = null;
        Rectangle curPaintArea = this.getVisibleRectangle();

        if (curPaintArea != null && screenToWorld != null) {
            Point2D p0 = new Point2D.Double(curPaintArea.getMinX(), curPaintArea.getMinY());
            Point2D p1 = new Point2D.Double(curPaintArea.getMaxX(), curPaintArea.getMaxY());
            screenToWorld.transform(p0, p0);
            screenToWorld.transform(p1, p1);

            aoi = new ReferencedEnvelope(Math.min(p0.getX(), p1.getX()), Math.max(p0.getX(), p1.getX()),
                    Math.min(p0.getY(), p1.getY()), Math.max(p0.getY(), p1.getY()),
                    mapContent.getCoordinateReferenceSystem());
        }

        return aoi;
    }

    @Override
    public DirectPosition2D getMapCenter() {
        AffineTransform tr = this.getScreenToWorldTransform();
        DirectPosition2D pos = new DirectPosition2D(this.getWidth() / 2, this.getHeight() / 2);
        tr.transform(pos, pos);
        pos.setCoordinateReferenceSystem(this.getMapContent().getCoordinateReferenceSystem());
        return pos;
    }

    @Override
    public void setDisplayArea(Envelope envelope) {
        if (envelope == null) {
            throw new IllegalArgumentException("envelope must not be null");
        }

        doSetDisplayArea(envelope);
        if (mapContent != null) {
            clearLabelCache = true;
            this.repaint(false);
        }
    }

    @Override
    public void reset() {
        if (mapContent != null) {
            setDisplayArea(mapContent.getMaxBounds());
        }
    }

    @Override
    public AffineTransform getScreenToWorldTransform() {
        if (mapContent != null) {

            if (screenToWorld == null) {
                this.setTransforms(this.getDisplayArea(), this.getVisibleRectangle());
                return new AffineTransform(screenToWorld);
            } else {
                return new AffineTransform(screenToWorld);
            }
        } else {
            return null;
        }
    }

    @Override
    public AffineTransform getWorldToScreenTransform() {
        if (mapContent != null) {

            if (worldToScreen == null) {
                this.setTransforms(this.getDisplayArea(), this.getVisibleRectangle());
                return new AffineTransform(worldToScreen);
            } else {
                return new AffineTransform(worldToScreen);
            }
        } else {
            return null;
        }
    }

    @Override
    public void addMapPaneListener(MapPaneListener listener) {
        this.paneListeners.add(listener);
    }

    @Override
    public void removeMapPaneListener(MapPaneListener listener) {
        this.paneListeners.remove(listener);
    }

    @Override
    public void addPlugin(Plugin plugin) {
        if (!this.plugins.contains(plugin)) {
            this.plugins.add(plugin);
            this.addPaintListener(plugin);
        }
    }

    @Override
    public void removePlugin(Plugin plugin) {
        if (this.plugins.contains(plugin)) {
            this.plugins.remove(plugin);
            this.removePaintListener(plugin);
        }
    }

    @Override
    public void addMouseListener(MapMouseListener listener) {
        if (this.mapMouseEventDispatcher != null) {
            this.mapMouseEventDispatcher.addMouseListener(listener);
        }
    }

    @Override
    public void removeMouseListener(MapMouseListener listener) {
        if (this.mapMouseEventDispatcher != null) {
            this.mapMouseEventDispatcher.removeMouseListener(listener);
        }
    }

    @Override
    public CursorTool getCursorTool() {
        return this.cursorTool;
    }

    @Override
    public void setCursorTool(CursorTool cursorTool) {
        if (this.cursorTool != null) {
            this.cursorTool.unUsed();
        }

        this.cursorTool = cursorTool;

        if (this.cursorTool != null) {
            this.cursorTool.setMapPane(this);
        }
    }

    /**
     * Helper method for {@linkplain #setDisplayArea} which is also called by
     * other methods that want to set the display area without provoking
     * repainting of the display
     *
     * @param envelope
     *            requested display area
     */
    private void doSetDisplayArea(Envelope envelope) {

        Rectangle curPaintArea = this.getVisibleRectangle();

        assert (mapContent != null && curPaintArea != null && !curPaintArea.isEmpty());

        if (equalsFullExtent(envelope)) {
            setTransforms(fullExtent, curPaintArea);
        } else {
            setTransforms(envelope, curPaintArea);
        }
        ReferencedEnvelope adjustedEnvelope = getDisplayArea();
        mapContent.getViewport().setBounds(adjustedEnvelope);

        // Publish the resulting display area with the event
        publishEvent(new MapPaneEvent(this, MapPaneEvent.Type.DISPLAY_AREA_CHANGED, getDisplayArea()));
    }

    /**
     * 发布map事件
     *
     * @param ev
     */
    private void publishEvent(MapPaneEvent ev) {

        for (MapPaneListener listener : paneListeners) {
            switch (ev.getType()) {
                case NEW_CONTEXT:
                    listener.onNewContext(ev);
                    break;

                case NEW_RENDERER:
                    listener.onNewRenderer(ev);
                    break;

                case PANE_RESIZED:
                    listener.onResized(ev);
                    break;

                case DISPLAY_AREA_CHANGED:
                    listener.onDisplayAreaChanged(ev);
                    break;

                case RENDERING_STARTED:
                    listener.onRenderingStarted(ev);
                    break;

                case RENDERING_STOPPED:
                    listener.onRenderingStopped(ev);
                    break;

                case RENDERING_PROGRESS:
                    listener.onRenderingProgress(ev);
                    break;
            }
        }
    }

    /**
     * 当获取worldtoscreen失败的时候，自己重新计算的worldtoscreen
     *
     * @param envelope
     * @param paintArea
     */
    private void setTransforms(final Envelope envelope, final Rectangle paintArea) {

        ReferencedEnvelope refEnv = null;
        if (envelope != null) {
            refEnv = new ReferencedEnvelope(envelope);
        } else {
            refEnv = worldEnvelope();
        }

        double xscale = paintArea.getWidth() / refEnv.getWidth();
        double yscale = paintArea.getHeight() / refEnv.getHeight();

        double scale = Math.min(xscale, yscale);

        double xoff = refEnv.getMedian(0) * scale - paintArea.getCenterX();
        double yoff = refEnv.getMedian(1) * scale + paintArea.getCenterY();

        worldToScreen = new AffineTransform(scale, 0, 0, -scale, -xoff, yoff);
        try {
            screenToWorld = worldToScreen.createInverse();
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if the envelope corresponds to full extent. It will probably not
     * equal the full extent envelope because of slack space in the display
     * area, so we check that at least one pair of opposite edges are equal to
     * the full extent envelope, allowing for slack space on the other two
     * sides.
     * <p>
     * Note: this method returns {@code false} if the full extent envelope is
     * wholly within the requested envelope (e.g. user has zoomed out from full
     * extent), only touches one edge, or touches two adjacent edges. In all
     * these cases we assume that the user wants to maintain the slack space in
     * the display.
     * <p>
     * This method is part of the work-around that the map pane needs because of
     * the differences in how raster and vector layers are treated by the
     * renderer classes.
     *
     * @param envelope
     *            a pending display envelope to compare to the full extent
     *            envelope
     *
     * @return true if the envelope is coincident with the full extent evenlope
     *         on at least two edges; false otherwise
     *
     * @todo My logic here seems overly complex - I'm sure there must be a
     *       simpler way for the map pane to handle this.
     */
    private boolean equalsFullExtent(final Envelope envelope) {
        if (fullExtent == null || envelope == null) {
            return false;
        }

        final double TOL = 1.0e-6d * (fullExtent.getWidth() + fullExtent.getHeight());

        boolean touch = false;
        if (Math.abs(envelope.getMinimum(0) - fullExtent.getMinimum(0)) < TOL) {
            touch = true;
        }
        if (Math.abs(envelope.getMaximum(0) - fullExtent.getMaximum(0)) < TOL) {
            if (touch) {
                return true;
            }
        }
        if (Math.abs(envelope.getMinimum(1) - fullExtent.getMinimum(1)) < TOL) {
            touch = true;
        }
        if (Math.abs(envelope.getMaximum(1) - fullExtent.getMaximum(1)) < TOL) {
            if (touch) {
                return true;
            }
        }

        return false;
    }

    //获取地图上下文图层的完整范围
    private void setFullExtent() {
        if (mapContent != null) {
            try {

                fullExtent = mapContent.getMaxBounds();

                //空图层或单一点要素时
                if (fullExtent == null) {
                    fullExtent = worldEnvelope();
                }
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        } else {
            fullExtent = null;
        }
    }

    //视界地图边界
    private ReferencedEnvelope worldEnvelope() {
        return new ReferencedEnvelope(-180, 180, -90, 90, DefaultGeographicCRS.WGS84);
    }
    
    /**
     * @Author YueLifeng
     * @Description //设置绘制对象
     * @Date 上午 9:03 2019/4/30 0030
     * @param newRenderer
     * @return void
     */
    private void doSetRenderer(GTRenderer newRenderer) {
        if (newRenderer != null) {
            Map<Object, Object> hints = newRenderer.getRendererHints();
            if (hints == null) {
                hints = new HashMap<Object, Object>();
            }

            if (newRenderer instanceof StreamingRenderer) {
                if (hints.containsKey(StreamingRenderer.LABEL_CACHE_KEY)) {
                    labelCache = (LabelCache) hints.get(StreamingRenderer.LABEL_CACHE_KEY);
                } else {
                    labelCache = new LabelCacheImpl();
                    hints.put(StreamingRenderer.LABEL_CACHE_KEY, labelCache);
                }
            }

            newRenderer.setRendererHints(hints);

            if (mapContent != null) {
                newRenderer.setMapContent(mapContent);
            }
        }

        renderer = newRenderer;
    }


}
