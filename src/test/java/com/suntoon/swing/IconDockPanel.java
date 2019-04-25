package com.suntoon.swing;

import com.javadocking.DockingManager;
import com.javadocking.dock.SingleDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.DraggableContent;
import com.javadocking.drag.DragListener;
import com.javadocking.model.FloatDockModel;
import com.suntoon.swing.resource.ResourceLoader;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName IconDockPanel
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/25 0025上午 11:01
 * @Version 1.0
 */
public class IconDockPanel extends JPanel {

    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 400;

    public IconDockPanel(JFrame frame) {

        super(new BorderLayout());

        // Create the dock model for the docks.
        FloatDockModel dockModel = new FloatDockModel();
        dockModel.addOwner("frame0", frame);

        // Give the dock model to the docking manager.
        DockingManager.setDockModel(dockModel);

        // Create the content component.
        TextPanel textPanel = new TextPanel("I am window 1.");

        // Create the dockable around the content component.
        Icon icon = new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_OPEN_LAYER));
        DefaultDockable dockable = new DefaultDockable("window1", textPanel, "Window", icon);
        dockable.setDescription("Window with text");

        // Create the single dock.
        SingleDock singleDock = new SingleDock();

        // Add the dockable to these tab docks.
        singleDock.addDockable(dockable, SingleDock.SINGLE_POSITION);

        // Add the root dock to the dock model.
        dockModel.addRootDock("singledock", singleDock, frame);

        // Add the dock to the panel.
        add(singleDock, BorderLayout.CENTER);
    }

    // This is the class for the content.
    private class TextPanel extends JPanel implements DraggableContent {

        private JLabel label;

        public TextPanel(String text) {

            super(new FlowLayout());

            // The panel.
            setMinimumSize(new Dimension(80, 80));
            setPreferredSize(new Dimension(150, 150));
            setBackground(Color.white);
            setBorder(BorderFactory.createLineBorder(Color.lightGray));

            // The label.
            label = new JLabel(text);
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(label);
        }

        @Override
        public void addDragListener(DragListener dragListener) {
            addMouseListener(dragListener);
            addMouseMotionListener(dragListener);
            label.addMouseMotionListener(dragListener);
            label.addMouseMotionListener(dragListener);
        }
    }

    // Main method.
    public static void createAndShowGUI() {

        // Create the frame.
        JFrame frame = new JFrame("Dockable with Icon and Tooltip");

        // Create the panel and add it to the frame.
        IconDockPanel panel = new IconDockPanel(frame);
        frame.getContentPane().add(panel);

        // Set the frame properties and show it.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Runnable doCreateAndShowGUI = new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        };

        SwingUtilities.invokeLater(doCreateAndShowGUI);
    }
}
