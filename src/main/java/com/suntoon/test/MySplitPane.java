package com.suntoon.test;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName MySplitPane
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/10 0010下午 2:28
 * @Version 1.0
 */
public class MySplitPane extends JSplitPane {

    private boolean hasProportionalLocation = false;
    private double proportionalLocation = 0.5;
    private boolean isPainted = false;

    @Override
    public void setDividerLocation(double proportionalLocation) {
        if (!isPainted) {
            hasProportionalLocation = true;
            this.proportionalLocation = proportionalLocation;
        } else {
            super.setDividerLocation(proportionalLocation);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!isPainted) {
            if (hasProportionalLocation) {
                super.setDividerLocation(proportionalLocation);
            }
            isPainted = true;
        }
    }
}
