package com.suntoon.map.event;

import com.suntoon.map.MapListener;
import com.suntoon.map.MapPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName DefaultMapMouseEventDispatcher
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/30 0030上午 9:09
 * @Version 1.0
 */
public class DefaultMapMouseEventDispatcher implements MapMouseEventDispatcher, MapListener {


    //当前的地图操作上下文
    private MapPane mapPane;

    //注册进来的事件列表
    private List<MapMouseListener> listeners;

    /**
     * @Author YueLifeng
     * @Description //创建一个新的管理器实例以使用指定的地图窗格
     * @Date 上午 9:23 2019/4/30 0030
     * @param mapPane
     *          地图窗口
     * @return
     */
    public DefaultMapMouseEventDispatcher(MapPane mapPane) {
        this.mapPane = mapPane;
        this.listeners = new ArrayList<MapMouseListener>();
    }

    @Override
    public boolean addMouseListener(MapMouseListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }

        if (!listeners.contains(listener)) {
            return listeners.add(listener);
        } else {
            return false;
        }
    }

    @Override
    public boolean removeMouseListener(MapMouseListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }

        return listeners.remove(listener);
    }

    @Override
    public void removeAllListeners() {
        listeners.clear();
    }

    @Override
    public List<MapMouseListener> getAllListeners() {
        return new ArrayList<>(this.listeners);
    }

    @Override
    public MapPane getMapPane() {
        return this.mapPane;
    }

    @Override
    public void setMapPane(MapPane mapPane) {
        this.mapPane = mapPane;
    }

    /**
     * @Author YueLifeng
     * @Description //接收鼠标单击事件并将派生的MapMouseEvent发送给监听器
     * @Date 上午 9:38 2019/4/30 0030
     * @param e
     * @return void
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        MapMouseEvent mapEv = convertEvent(e);
        if (mapEv != null) {
            for (MapMouseListener listener : listeners) {
                listener.onMouseClicked(mapEv);
            }
        }
    }

    /**
     * @Author YueLifeng
     * @Description //接收鼠标按住事件并将派生的MapMouseEvent发送给监听器
     * @Date 上午 9:38 2019/4/30 0030
     * @param e
     * @return void
     */
    @Override
    public void mousePressed(MouseEvent e) {
        MapMouseEvent mapEv = convertEvent(e);
        if (mapEv != null) {
            for (MapMouseListener listener : listeners) {
                listener.onMousePressed(mapEv);
            }
        }
    }

    /**
     * @Author YueLifeng
     * @Description //接收鼠标释放事件并将派生的MapMouseEvent发送给监听器
     * @Date 上午 9:38 2019/4/30 0030
     * @param e
     * @return void
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        MapMouseEvent mapEv = convertEvent(e);
        if (mapEv != null) {
            for (MapMouseListener listener : listeners) {
                listener.onMouseReleased(mapEv);
            }
        }
    }

    /**
     * @Author YueLifeng
     * @Description //接收鼠标输入事件并将派生的MapMouseEvent发送给监听器
     * @Date 上午 9:43 2019/4/30 0030
     * @param e
     * @return void
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        MapMouseEvent mapEv = convertEvent(e);
        if (mapEv != null) {
            for (MapMouseListener listener : listeners) {
                listener.onMouseEntered(mapEv);
            }
        }
    }

    /**
     * @Author YueLifeng
     * @Description //接收鼠标退出事件并将派生的MapMouseEvent发送给监听器
     * @Date 上午 9:45 2019/4/30 0030
     * @param e
     * @return void
     */
    @Override
    public void mouseExited(MouseEvent e) {
        MapMouseEvent mapEv = convertEvent(e);
        if (mapEv != null) {
            for (MapMouseListener listener : listeners) {
                listener.onMouseExited(mapEv);
            }
        }
    }

    /**
     * @Author YueLifeng
     * @Description //接收鼠标拖动事件并将派生的MapMouseEvent发送给监听器
     * @Date 上午 9:58 2019/4/30 0030
     * @param e
     * @return void
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        MapMouseEvent mapEv = convertEvent(e);
        if (mapEv != null) {
            for (MapMouseListener listener : listeners) {
                listener.onMouseDragged(mapEv);
            }
        }
    }

    /**
     * @Author YueLifeng
     * @Description //接收鼠标移动事件并将派生的MapMouseEvent发送给监听器
     * @Date 上午 10:02 2019/4/30 0030
     * @param e
     * @return void
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        MapMouseEvent mapEv = convertEvent(e);
        if (mapEv != null) {
            for (MapMouseListener listener : listeners) {
                listener.onMouseMoved(mapEv);
            }
        }
    }

    /**
     * @Author YueLifeng
     * @Description //接收鼠标滚轮事件并派生的MapMouseEvent发送给监听器
     * @Date 上午 10:05 2019/4/30 0030
     * @param e
     * @return void
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        MapMouseEvent mapEv = convertEvent(e);
        if (mapEv != null) {
            for (MapMouseListener listener : listeners) {
                listener.onMouseWheelMoved(mapEv);
            }
        }
    }

    @Override
    public MapMouseEvent convertEvent(MouseEvent ev) {
        MapMouseEvent mapEv = null;
        if (mapPane.getScreenToWorldTransform() != null) {
            mapEv = new MapMouseEvent(mapPane, ev);
        }

        return mapEv;
    }

    @Override
    public MapMouseEvent convertEvent(MouseWheelEvent ev) {
        MapMouseEvent mapEv = null;
        if (mapPane.getScreenToWorldTransform() != null) {
            mapEv = new MapMouseEvent(mapPane, ev);
        }

        return mapEv;
    }
}
