package com.suntoon.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * @ProjectionName desktop
 * @ClassName SpinnerWheelSupport
 * @Description //让微调控件支持滚轮的方法
 * @Author YueLifeng
 * @Date 2019/4/15 0015下午 5:27
 * @Version 1.0
 */
public class SpinnerWheelSupport {

    public static final String CLIENT_PROPERTY_MOUSE_WHEEL_LISTENER = "mouseWheelListener";
    protected static final String ACTION_NAME_INCREMENT = "increment";
    protected static final String ACTION_NAME_DECREMENT = "decrement";

    public static void installMouseWheelSupport(final JSpinner spinner) {
        MouseWheelListener l = new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (spinner == null || !spinner.isEnabled()) {
                    return;
                }
                int rotation = e.getWheelRotation();
                if (rotation < 0) {
                    Action action = spinner.getActionMap().get(ACTION_NAME_INCREMENT);
                    if (action != null) {
                        action.actionPerformed(new ActionEvent(e.getSource(), 0, ACTION_NAME_INCREMENT));
                    }
                }
                else if (rotation > 0) {
                    Action action = spinner.getActionMap().get(ACTION_NAME_DECREMENT);
                    if (action != null) {
                        action.actionPerformed(new ActionEvent(e.getSource(), 0, ACTION_NAME_DECREMENT));
                    }
                }
            }
        };
        spinner.addMouseWheelListener(l);
        spinner.putClientProperty(CLIENT_PROPERTY_MOUSE_WHEEL_LISTENER, l);
    }

    public static void uninstallMouseWheelSupport(final JSpinner spinner) {
        MouseWheelListener l = (MouseWheelListener) spinner.getClientProperty(CLIENT_PROPERTY_MOUSE_WHEEL_LISTENER);
        if (l != null) {
            spinner.removeMouseWheelListener(l);
        }
    }
}
