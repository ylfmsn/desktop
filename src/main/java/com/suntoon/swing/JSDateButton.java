package com.suntoon.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName JSDateButton
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/15 0015下午 5:19
 * @Version 1.0
 */
public class JSDateButton extends JButton {

    private static final long serialVersionUID = 1L;
    protected Color normalBackground;
    protected Color selectedBackground;
    
    public JSDateButton() {
        initAttributes();
    }

    public JSDateButton(Icon icon) {
        super(icon);
        initAttributes();
    }

    public JSDateButton(String text, Icon icon) {
        super(text, icon);
        initAttributes();
    }

    public JSDateButton(String text) {
        super(text);
        initAttributes();
    }

    public JSDateButton(Action a) {
        super(a);
        initAttributes();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Paint oldPaint = g2d.getPaint();

        if ((getModel().isSelected() || getModel().isPressed()) && selectedBackground != null) {
            g2d.setPaint(selectedBackground);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        } else if (getNormalBackground() != null) {
            g2d.setPaint(getNormalBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
        g2d.setPaint(oldPaint);
        super.paint(g2d);
    }

    private void initAttributes() {
        setRolloverEnabled(true);
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setFocusPainted(false);
        setNormalBackground(new Color(216, 216, 216));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void clearDefaultAttribute() {

        setNormalBackground(null);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        normalBackground = bg;
    }

    public Color getNormalBackground() {

        return normalBackground;
    }

    public void setNormalBackground(Color normalBackground) {
        super.setBackground(normalBackground);
        this.normalBackground = normalBackground;
    }

    public void setSelectedBackground(Color selectedBackground) {

        this.selectedBackground = selectedBackground;
    }
}
